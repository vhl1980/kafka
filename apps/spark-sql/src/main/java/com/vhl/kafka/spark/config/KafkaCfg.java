package com.vhl.kafka.spark.config;

import java.util.HashMap;

public class KafkaCfg {
	private Common common;
	private Consumer consumer;

	public Common getCommon() {
		return common;
	}

	public void setCommon(Common common) {
		this.common = common;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public static class Common{
		private HashMap<String, String> properties;

		public HashMap<String, String> getProperties() {
			return properties;
		}

		public void setProperties(HashMap<String, String> properties) {
			this.properties = properties;
		}
	}




}
