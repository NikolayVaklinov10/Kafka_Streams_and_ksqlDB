package com.nikolayvaklinov.kafkaStreams.ProcessorAPI;

import java.time.Instant;

import com.nikolayvaklinov.kafkaStreams.ProcessorAPI.model.TurbineState;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

/** This class allows us to use event-time semantics for purchase streams */
public class TurbineStateTimestampExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> record, long partitionTime) {
        TurbineState measurement = (TurbineState) record.value();
        if (measurement != null && measurement.getTimestamp() != null) {
            String timestamp = measurement.getTimestamp();
            // System.out.println("Extracting timestamp: " + timestamp);
            return Instant.parse(timestamp).toEpochMilli();
        }
        // fallback to stream time
        return partitionTime;
    }
}