package com.vhl.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vhl.kafka.config.ConfigApp;
import com.vhl.kafka.connexion.KafkaConfigClient;
import com.vhl.kafka.options.OptionsApp;
import com.vhl.kafka.process.ProcessProduce;
import com.vhl.kafka.process.ProcessStream;

import picocli.CommandLine;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		//String[] t_args = { "-f","config/app.yaml","-l","produce"};
		ConfigApp confApp = CommandLine.call(new OptionsApp(), args);

		logger.info("Process : "+confApp.getProcess());
		KafkaConfigClient cfgClient = new KafkaConfigClient(confApp.getKafkaCfg().getCommon().getProperties());
		

		switch (confApp.getProcess()) {
		case produce:
			cfgClient.loadCLientProperties(confApp.getKafkaCfg().getProducer().getProperties());
			cfgClient.getPropsClient().forEach((k, v) -> logger.info(k + ":" + v));
			try {
				ProcessProduce p = new ProcessProduce(cfgClient.getPropsClient(), confApp.getKafkaCfg().getProducer());
				p.call();
			} catch (IOException e) {
				logger.error(e.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			break;
		case stream:
			cfgClient.loadCLientProperties(confApp.getKafkaCfg().getStream().getProperties());
			cfgClient.getPropsClient().forEach((k, v) -> logger.info(k + ":" + v));
			try {
				ProcessStream p = new ProcessStream(cfgClient.getPropsClient(), confApp.getKafkaCfg().getStream().getTopic_name());
				p.call();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			break;
		default:
			logger.error("PROCESS NO EXIST");
			break;
		}
	}

}
