package com.product.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {
	
	@Autowired
	private KafkaTemplate<String, String> ktemp;
	
	public void sendmessage(String topicname,String m) {
		System.out.println(m+ "sending message to topic "+topicname);
		ktemp.send(topicname,m);
	}

}
