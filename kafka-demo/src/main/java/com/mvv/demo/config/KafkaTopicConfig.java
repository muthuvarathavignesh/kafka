package com.mvv.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

	@Value("${app.kafka.topic}")
	private String topicName;
	
	@Value("${app.kafka.partitions}")	
	private int partitions;

	@Bean
	public NewTopic demoTopic() {
		return new NewTopic(topicName, partitions, (short) 1);
	}
}
