package com.product.utility;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class SenderConfigure {
	
	@Value("${kafka.bootstrapAddress}")
	private String kafkaserver;
	
	
	@Bean
	public Map<String, Object> configs(){
		Map<String, Object> pp=new HashMap<String, Object>();
		pp.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaserver);
		pp.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		pp.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		return pp;
	}
	
	
	
	@Bean
	public ProducerFactory<String,String> producerFactory(){
		return new DefaultKafkaProducerFactory<>(configs());
	}
	
	@Bean
	public KafkaTemplate<String, String> configTemplate(){
		return new KafkaTemplate<String, String>(producerFactory());
	}
	
	@Bean
	public Sender configSender() {
		return new Sender();
	}

}







