package com.vhl.kafka.process;

import java.util.Properties;
import java.util.concurrent.Callable;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vhl.kafka.config.Stream;
import com.vhl.kafka.model.FLowSerder;
import com.vhl.kafka.model.Flow;

public class ProcessStream implements Callable<Boolean>{

	private static final Logger logger = LoggerFactory.getLogger(ProcessStream.class);
	private StreamsBuilder builder;
	private Properties props;
	private String topic;
	
	public ProcessStream(Properties propsClient, String topic_name) {
		builder = new StreamsBuilder();
		props = propsClient;
		topic = topic_name;
	}

	public Boolean call() throws Exception {
        FLowSerder flowSerde = new FLowSerder();
        KStream<String, Flow> dataStream = builder.stream(topic,
                Consumed.with(Serdes.String(), flowSerde.getFlowSerde()));

        dataStream.foreach((k, v) -> logger.info("KEY : " + k + " value : " + v.toString()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
		return true;
	}

}
