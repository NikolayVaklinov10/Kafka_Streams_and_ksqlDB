package com.nikolayvaklinov.kafkaStreams.WindowsAndTime;

import com.nikolayvaklinov.kafkaStreams.WindowsAndTime.model.Vital;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import java.time.Instant;

/** This class allows us to use event-time semantics for purchase streams */
public class VitalTimestampExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> record, long partitionTime) {
        Vital measurement = (Vital) record.value();
        if (measurement != null && measurement.getTimestamp() != null) {
            String timestamp = measurement.getTimestamp();
            // System.out.println("Extracting timestamp: " + timestamp);
            return Instant.parse(timestamp).toEpochMilli();
        }
        // fallback to stream time
        return partitionTime;
    }
}