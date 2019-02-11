package com.vhl.kafka.config;

import java.util.Map;

public class ConfigApp {

	private String version;
	private Map<String, String> kafka_common;
	private KafkaProducerCfg  kafka_producer_cfg;
	private Map<String, String> kafka_producer_properties;
	private EnumProcess process;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, String> getKafka_common() {
		return kafka_common;
	}

	public void setKafka_common(Map<String, String> kafka_common) {
		this.kafka_common = kafka_common;
	}

	public KafkaProducerCfg getKafka_producer_cfg() {
		return kafka_producer_cfg;
	}

	public void setKafka_producer_cfg(KafkaProducerCfg kafka_producer_cfg) {
		this.kafka_producer_cfg = kafka_producer_cfg;
	}

	public Map<String, String> getKafka_producer_properties() {
		return kafka_producer_properties;
	}

	public void setKafka_producer_properties(Map<String, String> kafka_producer_properties) {
		this.kafka_producer_properties = kafka_producer_properties;
	}

	public EnumProcess getProcess() {
		return process;
	}

	public void setProcess(EnumProcess process) {
		this.process = process;
	}


	
	
	
}
