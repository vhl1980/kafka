package com.vhl.kafka.spark.process;

import java.util.Iterator;
import java.util.List;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;

public class ProcessCasRead {

	private SparkSession spark;
	private static final Logger logger = LoggerFactory.getLogger(ProcessCasRead.class);
	public ProcessCasRead(SparkSession spark) {
		this.spark = spark;
	}

	public void excute() {
		
		 
		
		JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
		CassandraConnector connection = CassandraConnector.apply(jsc.getConf());
		//JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
		
		
//		JavaRDD<String> cassandraRowsRDD = javaFunctions(sc).cassandraTable("ks", "people")
//		        .map(new Function<CassandraRow, String>() {
//		            @Override
//		            public String call(CassandraRow cassandraRow) throws Exception {
//		                return cassandraRow.toString();
//		            }
//		        });
		
		//JavaRDD<TagPost> rdd = javaFunctions(jsc).cassandraTable("skc", "tag_post", mapRowTo(TagPost.class));
		logger.info("To connect cassandra");
		try {
			Session cassandra = connection.openSession();
			logger.info("connected cassandra");
			String cql = "select * from skc.tag_post";
			ResultSet rs = cassandra.execute(cql);
		
			
			List<com.datastax.driver.core.Row> rsList = rs.all();
			logger.info("**************");
			Iterator<com.datastax.driver.core.Row> it = rsList.iterator();
			while (it.hasNext()) {
				com.datastax.driver.core.Row r = it.next();
				String test = r.getString("post_key");
				logger.info(test);
			}
			
			cassandra.close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println("--------------------------------------------------------------  UUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			System.out.println(e.getMessage());
		}
	}
	
	
	
}
