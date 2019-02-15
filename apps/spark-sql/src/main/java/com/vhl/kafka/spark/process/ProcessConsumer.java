package com.vhl.kafka.spark.process;

import java.util.concurrent.Callable;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProcessConsumer implements Callable<Dataset>{
	
	private static final Logger logger = LoggerFactory.getLogger(Process.class);
	private String topic;
	private String broker;
	private SparkSession spark;
	
	
	public ProcessConsumer(String broker,String topic_name, SparkSession spark) {
		this.broker = broker;
		topic = topic_name;
		this.spark = spark;
	}

	@Override
	public Dataset call() throws Exception {
		Dataset<Row> df = spark
				  .read()
				  .format("kafka")
				  .option("kafka.bootstrap.servers", broker)
				  .option("subscribe", topic)
				  .option("startingOffsets", "earliest")
				  .option("endingOffsets", "latest")
				  .load();
		
		//df.show();

		return df;
	}

}
