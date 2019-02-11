package com.vhl.kafka.options;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.vhl.kafka.config.ConfigApp;
import com.vhl.kafka.config.EnumProcess;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(description = "Process apps kafka",
name = "Process app", mixinStandardHelpOptions = true, version = "Process App 1.0")
public class OptionsApp implements Callable<ConfigApp> {

	private static final Logger logger = LoggerFactory.getLogger(OptionsApp.class);

	//private enum Process { produce, streams }

	@Option(names = "-l", required = true,description = "@|fg(green) Enum values: ${COMPLETION-CANDIDATES}|@")
	private EnumProcess process = null;

	@Option(names = "-f", required = true, description = "File configuration APP Kafka")
	private File config = null;

	@Override
	public ConfigApp call() throws Exception {
		logger.info("--------- OPTIONS SETTING -------------");
		if (!config.exists()) {
			logger.error("Files not exist : "+config.getName());
			System.exit(1);
		}
		ConfigApp confYaml = null;
		Yaml yaml = new Yaml();  

		try{
			logger.info("LOAD CONFIG");
			InputStream in = Files.newInputStream(Paths.get(config.getPath()));
			confYaml = yaml.loadAs( in, ConfigApp.class );
			
			confYaml.setProcess(process);
			
			return confYaml;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return null;
	}
}
