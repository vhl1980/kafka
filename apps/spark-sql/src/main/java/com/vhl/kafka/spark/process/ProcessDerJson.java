package com.vhl.kafka.spark.process;

import java.util.HashMap;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataType;
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
		
	    
	    
	    //.select(from_json(col("value").cast("string"), schema, jsonOptions).alias("parsed_value"))
	}
	
	public Dataset<Row> derJson() {
		
		//df.withColumns("value", functions.from_json(df.col("value"), schema), new HashMap<>());
		
		Dataset<Row> derNadia =  df.withColumn("value",
	            functions.from_json(df.col("value"),schema, new HashMap<>()));
		
		Dataset<Row> der =  df.select(functions.from_json(functions.col("value"),schema).as("json"));

		derNadia.printSchema();
		derNadia.show();
		
		logger.info("--------------------");
		
		der.printSchema();
		der.show();
		
		return null;
		
	}

}
