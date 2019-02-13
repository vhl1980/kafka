package com.vhl.kafka.spark.connexion;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class KafkaConfig {
	final protected Properties props;

	private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

	/**
	 * Contruct Global config
	 */
	public KafkaConfig(Map<String, String> commonProperties) {
		this.props = new Properties();
		props.putAll(commonProperties);
	}

	public abstract void loadCLientProperties(Map<String, String> clientProperties);

}

