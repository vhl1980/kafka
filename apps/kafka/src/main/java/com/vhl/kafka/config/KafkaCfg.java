package com.vhl.kafka.config;

import java.util.HashMap;

public class KafkaCfg {
	private Common common;
	private Producer producer;
	private Stream stream;
	
	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public Common getCommon() {
		return common;
	}

	public void setCommon(Common common) {
		this.common = common;
	}

	public Stream getStream() {
		return stream;
	}

	public void setStream(Stream stream) {
		this.stream = stream;
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
