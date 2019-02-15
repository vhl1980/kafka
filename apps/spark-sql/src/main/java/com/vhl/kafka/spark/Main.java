package com.vhl.kafka.spark;

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
		SparkConf conf = new SparkConf()
				.set("spark.cassandra.connection.host", "wk1")
		        .set("spark.cassandra.auth.username", "adm")            
		        .set("spark.cassandra.auth.password", "adm")
				.setAppName("SPARK SUPERSET");
		
		
		SparkSession spark = SparkSession
				  .builder()
				  .config(conf)
				  .getOrCreate();
		
		
		
//		spark.sparkContext().addFile("app.yaml");
//		String te = SparkFiles.getRootDirectory();
//		//SparkFiles.get("app.yaml");
//		String d = te+"/app.yaml";
//		logger.info(d);
//		logger.info("*************************");
//		String[] t_args = { "-p","produce","-f", d};
//		
//		logger.info("TOOT : "+te);
		
		ConfigApp confApp = CommandLine.call(new OptionsApp(), args);
		logger.info("Process : "+confApp.getProcess());


		//		logger.info(confApp.getVersion());

		//		

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
							Dataset<Row> df = p.call();
							//df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");
							Dataset<Row> serJson = df.selectExpr("CAST(value AS STRING)");
							serJson.show();
							ProcessDerJson pDer = new ProcessDerJson(serJson);
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

	}
}
