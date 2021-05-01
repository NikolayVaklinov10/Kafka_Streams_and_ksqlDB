package com.nikolayvaklinov.testingKafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;

public class MyTopology {

    public Topology build() {
        StreamsBuilder builder = new StreamsBuilder();
        builder
                .stream("events", Consumed.with(Serdes.String(), Serdes.ByteArray()))
                .selectKey(MyTopology::decodeKey)
                .to("events-repartitioned");
        return builder.build();
    }

    public static String decodeKey(String key, byte[] payload) {
        String newKey = String.format("decoded-%s", key);
        return newKey;
    }
}
