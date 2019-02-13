package com.vhl.kafka.spark.config;

import java.util.HashMap;

public class Consumer {
	private String topic_name;
	private HashMap<String, String> properties;

	public HashMap<String, String> getProperties() {
		return properties;
	}

	public void setProperties(HashMap<String, String> properties) {
		this.properties = properties;
	}

	public String getTopic_name() {
		return topic_name;
	}

	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}
}
