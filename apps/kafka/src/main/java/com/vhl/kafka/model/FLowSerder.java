/*
 * Creation : 24 janv. 2019
 */
package com.vhl.kafka.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

public class FLowSerder {

    private Serde<Flow> flowSerde;

    public FLowSerder() {
        Map<String, Object> serdeProps = new HashMap<>();

        final Serializer<Flow> flowSer = new SerJson<>();
        serdeProps.put("JsonPOJOClass", Flow.class);
        flowSer.configure(serdeProps, false);

        final Deserializer<Flow> flowDer = new DerJson<>();
        serdeProps.put("JsonPOJOClass", Flow.class);
        flowDer.configure(serdeProps, false);
        this.setFlowSerde(Serdes.serdeFrom(flowSer, flowDer));

    }

    public Serde<Flow> getFlowSerde() {
        return flowSerde;
    }

    private void setFlowSerde(Serde<Flow> flowSerde) {
        this.flowSerde = flowSerde;
    }

}
