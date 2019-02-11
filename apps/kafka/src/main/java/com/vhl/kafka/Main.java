package com.vhl.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vhl.kafka.config.ConfigApp;
import com.vhl.kafka.config.EnumProcess;
import com.vhl.kafka.connexion.KafkaConfigProducer;
import com.vhl.kafka.options.OptionsApp;
import com.vhl.kafka.process.ProcessProduce;

import picocli.CommandLine;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		String[] t_args = { "-f","config/app.yaml","-l","produce"};
		ConfigApp confApp = CommandLine.call(new OptionsApp(), t_args);

		logger.info(confApp.getKafka_producer_cfg().toString());
		logger.info("Process : "+confApp.getProcess());


		switch (confApp.getProcess()) {
		case produce:
			logger.info("LOAD PpP");
			KafkaConfigProducer cfgProducer = new KafkaConfigProducer(confApp.getKafka_common());
			cfgProducer.loadCLientProperties(confApp.getKafka_producer_properties());
			cfgProducer.getPropsProducer().forEach((k, v) -> logger.info(k + ":" + v));
			try {
				ProcessProduce p = new ProcessProduce(cfgProducer.getPropsProducer());
				p.execute(confApp.getKafka_producer_cfg());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			break;
		case streams:
			logger.info("LOAD");
			break;
		default:
			logger.error("PROCESS NO EXIST");
			break;
		}

	}

}
