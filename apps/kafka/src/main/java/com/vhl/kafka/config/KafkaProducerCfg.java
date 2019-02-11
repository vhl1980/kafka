package com.vhl.kafka.config;

import static java.lang.String.format;

public class KafkaProducerCfg {
	private Integer messages_send;
	private String topic_name;
	private String data_file;

	public String getTopic_name() {
		return topic_name;
	}
	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}
	public String getData_file() {
		return data_file;
	}
	public void setData_file(String data_file) {
		this.data_file = data_file;
	}
	public Integer getMessages_send() {
		return messages_send;
	}
	public void setMessages_send(Integer messages_send) {
		this.messages_send = messages_send;
	}

    @Override
    public String toString() {
        return new StringBuilder()
            .append( format( "Number message send: %d\n", messages_send ) )
            .append( format( "Topic name: %s\n", topic_name ) )
            .append( format( "Data file : %s\n", data_file ) )
            .toString();
    }
	
}
