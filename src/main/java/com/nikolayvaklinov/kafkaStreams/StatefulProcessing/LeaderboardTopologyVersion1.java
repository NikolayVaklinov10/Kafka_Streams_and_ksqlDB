package com.nikolayvaklinov.kafkaStreams.StatefulProcessing;

import com.nikolayvaklinov.kafkaStreams.StatefulProcessing.model.Player;
import com.nikolayvaklinov.kafkaStreams.StatefulProcessing.model.Product;
import com.nikolayvaklinov.kafkaStreams.StatefulProcessing.model.ScoreEvent;
import com.nikolayvaklinov.kafkaStreams.StatefulProcessing.serialization.json.JsonSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

class LeaderboardTopologyVersion1 {

    /**
     * This is the initial version of the topology
     */
    public static Topology build() {
        StreamsBuilder builder = new StreamsBuilder();

        KStream<byte[], ScoreEvent> scoreEvents =
                builder.stream("score-events", Consumed.with(Serdes.ByteArray(), JsonSerdes.ScoreEvent()));

        KTable<String, Player> players =
                builder.table("players", Consumed.with(Serdes.String(), JsonSerdes.Player()));

        GlobalKTable<String, Product> products =
                builder.globalTable("products", Consumed.with(Serdes.String(), JsonSerdes.Product()));

        scoreEvents.print(Printed.<byte[], ScoreEvent>toSysOut().withLabel("score-events"));
        players.toStream().print(Printed.<String, Player>toSysOut().withLabel("players"));
        // there's no print or toStream().print() option for GlobalKTables

        return builder.build();
    }
}