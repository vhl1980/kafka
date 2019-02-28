package com.vhl.kafka.spark;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vhl.kafka.spark.config.ConfigApp;
import com.vhl.kafka.spark.options.OptionsApp;
import com.vhl.kafka.spark.process.ProcessCasRead;
import com.vhl.kafka.spark.process.ProcessConsumer;
import com.vhl.kafka.spark.process.ProcessDerJson;

import picocli.CommandLine;
public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {		

		//String[] t_args = { "-p","produce","-f","config/app.yaml"};

		ConfigApp confApp = CommandLine.call(new OptionsApp(), args);
		logger.info("Process : "+confApp.getProcess());

		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(confApp.getFileConfig());
			// load a properties file
			prop.load(input);

			String brokers = prop.getProperty("kafka.brokers");
			String topic= prop.getProperty("kafka.topic");
			logger.info("------------------------- KAFKA BROKERS : "+brokers);
			logger.info("------------------------- KAFKA TOPIC : "+topic);

			SparkConf conf = new SparkConf()
					.setAppName("SPARK SUPERSET");


			SparkSession spark = SparkSession
					.builder()
					.config(conf)
					.getOrCreate();	

			switch (confApp.getProcess()) {
			case produce:
				break;
			case consume:
				ProcessConsumer p = new ProcessConsumer(brokers,topic,spark);
				try {
					Dataset<Row> df = p.call();
					df.printSchema();
					//df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");
					Dataset<Row> serJson = df.selectExpr("CAST(value AS STRING)");
					serJson.show();
					ProcessDerJson pDer = new ProcessDerJson(serJson);
					pDer.derJson();
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				break;
			case casRead:
				ProcessCasRead pCas = new ProcessCasRead(spark);
				pCas.excute();	
				break;
			default:
				logger.error("PROCESS NO EXIST");
				break; 
			}
			spark.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}


	}


}
