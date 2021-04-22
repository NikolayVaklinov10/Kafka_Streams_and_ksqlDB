package com.nikolayvaklinov.kafkaStreams.ProcessorAPI.serialization.json;

import com.nikolayvaklinov.kafkaStreams.ProcessorAPI.model.DigitalTwin;
import com.nikolayvaklinov.kafkaStreams.ProcessorAPI.model.TurbineState;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class JsonSerdes {

    public static Serde<DigitalTwin> DigitalTwin() {
        JsonSerializer<DigitalTwin> serializer = new JsonSerializer<>();
        JsonDeserializer<DigitalTwin> deserializer = new JsonDeserializer<>(DigitalTwin.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<TurbineState> TurbineState() {
        JsonSerializer<TurbineState> serializer = new JsonSerializer<>();
        JsonDeserializer<TurbineState> deserializer = new JsonDeserializer<>(TurbineState.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
