package com.vhl.kafka.spark.process;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessDerJson {

	private Dataset<Row> df;
	private StructType schema;
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessDerJson.class);
	
	public ProcessDerJson(Dataset<Row> serJson)  {
		df = serJson;
	    StructField[] structFields = new StructField[]{
	            new StructField("id", DataTypes.IntegerType, true, Metadata.empty()),
	            new StructField("lat", DataTypes.IntegerType, true, Metadata.empty()),
	            new StructField("lon", DataTypes.IntegerType, true, Metadata.empty()),
	            new StructField("alert", DataTypes.IntegerType, true, Metadata.empty()),
	            new StructField("status", DataTypes.BooleanType, true, Metadata.empty()),
	    };
		
	    schema = new StructType(structFields);
	}
	
	public Dataset<Row> derJson() {
		Dataset<Row> der =  df.select(functions.from_json(functions.col("value"),schema).as("json")).select("json.*");
		
		logger.info("--------------------");
		
		der.printSchema();
		der.show();
		
		return null;
		
	}

}
