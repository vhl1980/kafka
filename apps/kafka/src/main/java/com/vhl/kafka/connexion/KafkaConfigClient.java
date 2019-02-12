package com.vhl.kafka.connexion;

import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConfigClient extends KafkaConfig{

    final private Properties propsClient;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConfigClient.class);
	
	public KafkaConfigClient(Map<String, String> commonProperties) {
		super(commonProperties);
		this.propsClient = new Properties();
		this.propsClient.putAll(super.props);
	}

	@Override
	public void loadCLientProperties(Map<String, String> clientProperties) {
		logger.info("LOAD CLIENT PROPERTIES");
		propsClient.putAll(clientProperties);
	}

    public Properties getPropsClient() {
        return propsClient;
    }

}
