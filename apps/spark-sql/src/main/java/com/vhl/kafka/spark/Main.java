package com.vhl.kafka.spark;

import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vhl.kafka.spark.config.ConfigApp;
import com.vhl.kafka.spark.options.OptionsApp;
import com.vhl.kafka.spark.process.ProcessConsumer;

import picocli.CommandLine;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		//String[] t_args = { "-p","consume"};
		ConfigApp confApp = CommandLine.call(new OptionsApp(), args);
		logger.info("Process : "+confApp.getProcess());

		
		SparkSession spark = SparkSession
				  .builder()
				  .appName("Java Spark SQL basic example")
				  .getOrCreate();
		
		
//		Dataset<Row> df = spark
//				  .read()
//				  .format("kafka")
//				  .option("kafka.bootstrap.servers", "adm:9092")
//				  .option("subscribe", "lab")
//				  .option("startingOffsets", "earliest")
//				  .option("endingOffsets", "latest")
//				  .load();
//		df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");
//		df.show(); 
		
//		KafkaConfigClient cfgClient = new KafkaConfigClient(confApp.getKafkaCfg().getCommon().getProperties());
		
		switch (confApp.getProcess()) {
		case produce:
			break;
		case consume:

			//cfgClient.loadCLientProperties(confApp.getKafkaCfg().getConsumer().getProperties());
			//cfgClient.getPropsClient().forEach((k, v) -> logger.info(k + ":" + v));
			
			

			ProcessConsumer p = new ProcessConsumer("adm:9092","lab",spark);
			try {
				p.call();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			break;
		default:
			logger.error("PROCESS NO EXIST");
			break; 
//
//
		}
//
	}
}
