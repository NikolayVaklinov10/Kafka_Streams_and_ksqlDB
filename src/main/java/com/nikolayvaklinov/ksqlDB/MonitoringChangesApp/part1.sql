/* The following is a custom type  */
CREATE TYPE season_length AS STRUCT<season_id INT, episode_count INT> ;

/* Creating a source collection this is a table and the id Primary key is unique for it */
CREATE TABLE titles (
                        id INT PRIMARY KEY,
                        title VARCHAR
) WITH (
      KAFKA_TOPIC='titles', /* this is the name of the topic the table will create */
      VALUE_FORMAT='AVRO', /* the serialization bit of the table */
      PARTITIONS=4  /* there will be 4 partitions of it */
      );

/* The following is a NON Persistent stream meaning it will not survive restart of ksqlDB server */
CREATE STREAM production_changes (
    rowkey VARCHAR KEY,
    uuid INT,
    title_id INT,
    change_type VARCHAR,
    before season_length, /* the custom types created before will be used here */
    after season_length,
    created_at VARCHAR
) WITH (
    KAFKA_TOPIC='production_changes',
    PARTITIONS='4',
    VALUE_FORMAT='JSON',
    TIMESTAMP='created_at',
    TIMESTAMP_FORMAT='yyyy-MM-dd HH:mm:ss'
);

CREATE STREAM season_length_changes
WITH (
    KAFKA_TOPIC = 'season_length_changes',
    VALUE_FORMAT = 'AVRO',
    PARTITIONS = 4,
    REPLICAS = 1
) AS SELECT
                       ROWKEY,
                       title_id,
                       IFNULL(after->season_id, before->season_id) AS season_id,
                       before->episode_count AS old_episode_count,
                       after->episode_count AS new_episode_count,
                       created_at
     FROM production_changes
     WHERE change_type = 'season_length'
                       EMIT CHANGES ;