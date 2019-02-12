package com.vhl.kafka.config;

public class ConfigApp {

	private String version;
	private KafkaCfg kafkaCfg;
	private EnumProcess process;
	

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	public EnumProcess getProcess() {
		return process;
	}

	public void setProcess(EnumProcess process) {
		this.process = process;
	}

	public KafkaCfg getKafkaCfg() {
		return kafkaCfg;
	}

	public void setKafkaCfg(KafkaCfg kafkaCfg) {
		this.kafkaCfg = kafkaCfg;
	}


	
	
	
}
