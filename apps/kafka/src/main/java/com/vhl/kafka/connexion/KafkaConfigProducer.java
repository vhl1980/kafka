package com.vhl.kafka.connexion;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConfigProducer extends KafkaConfig{

    final private Properties propsProducer;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConfigProducer.class);
	
	public KafkaConfigProducer(Map<String, String> commonProperties) {
		super(commonProperties);
		this.propsProducer = new Properties();
		this.propsProducer.putAll(super.props);
	}

	@Override
	public void loadCLientProperties(Map<String, String> clientProperties) {
		logger.info("LOAD CLIENT PRODUCER PROPERTIES");
		propsProducer.putAll(clientProperties);
	}

    public Properties getPropsProducer() {
        return propsProducer;
    }

}
