package com.vhl.kafka.spark.options;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import com.vhl.kafka.spark.config.ConfigApp;
import com.vhl.kafka.spark.config.EnumProcess;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(description = "Process apps spark kafka",
name = "Process app", mixinStandardHelpOptions = true, version = "Process App 1.0")
public class OptionsApp implements Callable <ConfigApp> {

	private static final Logger logger = LoggerFactory.getLogger(OptionsApp.class);


	@Option(names = "-p", required = true,description = "@|fg(green) Enum values: ${COMPLETION-CANDIDATES}|@")
	private EnumProcess process = null;

	@Override
	public ConfigApp call() throws Exception {
		logger.info("--------- OPTIONS SETTING -------------");

		ConfigApp confYaml = new ConfigApp();
	  

		try{
			logger.info("LOAD CONFIG");
			
			confYaml.setProcess(process);
			
			return confYaml;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return null;
	}

}
