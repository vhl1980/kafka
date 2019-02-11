package com.vhl.kafka.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vhl.kafka.config.KafkaProducerCfg;
import com.vhl.kafka.model.FLowSerder;
import com.vhl.kafka.model.Flow;

public class ProcessProduce {
    private static final Logger logger = LoggerFactory.getLogger(ProcessProduce.class);

    // final Producer<Long, String> producer;
    long time = System.currentTimeMillis();

    private KafkaProducer<String, Flow> producer;

    public ProcessProduce(Properties propKafka) throws IOException {
        FLowSerder flowSerde = new FLowSerder();
        producer = new KafkaProducer<>(propKafka, Serdes.String().serializer(), flowSerde.getFlowSerde().serializer());
        
    }

	public void execute(KafkaProducerCfg kafka_producer_cfg) {
		Integer sendMessageCount = kafka_producer_cfg.getMessages_send();
		String topic = kafka_producer_cfg.getTopic_name();
		final CountDownLatch countDownLatch = new CountDownLatch(sendMessageCount);
        Path path = Paths.get(kafka_producer_cfg.getData_file());
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (long index = time; index < time + sendMessageCount; index++) {
                BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
                String currentLine = null;
                logger.info("**************************************");
                while ((currentLine = reader.readLine()) != null) {
                    Flow fl = mapper.readValue(currentLine, Flow.class);
                    final ProducerRecord<String, Flow> record = new ProducerRecord<String, Flow>(topic, fl);
                    producer.send(record, (metadata, exception) -> {
                        long elapsedTime = System.currentTimeMillis() - time;
                        if (metadata != null) {
                            System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n", record.key(),
                                    record.value().toString(), metadata.partition(), metadata.offset(), elapsedTime);
                        } else {
                            exception.printStackTrace();
                        }
                        countDownLatch.countDown();
                    });
                }
            }
            countDownLatch.await(25, TimeUnit.SECONDS);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (InterruptedException e) {
        	logger.error(e.getMessage());
		} finally {
            producer.flush();
            producer.close();
        }
		
	}
	
    
	
}
