package com.product.utility;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class ReceiverConfigure {
	
	@Value("${kafka.bootstrapAddress}")
	private String kafkaserver;
	
	
	@Bean
	public Map<String, Object> configs1(){
		Map<String, Object> pp=new HashMap<String, Object>();
		pp.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaserver);
		pp.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		pp.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		pp.put(ConsumerConfig.CLIENT_ID_CONFIG,"myspgroup");
		pp.put(ConsumerConfig.GROUP_ID_CONFIG,"myspgroup1");
		
		return pp;
	}
	
	
	
	@Bean
	public ConsumerFactory<String,String> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(configs1());
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> configListener(){
		
		ConcurrentKafkaListenerContainerFactory<String, String> f=new ConcurrentKafkaListenerContainerFactory<>();
		
		f.setConsumerFactory(consumerFactory());
		
		return f;
	}
	
	@Bean
	public Receiver configReceiver() {
		return new Receiver();
	}

}







