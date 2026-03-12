package com.mvv.demo.service.kafka;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mvv.demo.dto.UserEvent;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

	@Value("${app.kafka.topic}")
	private String topicName;
	
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public void sendEvent(UserEvent event) {
        kafkaTemplate.send(topicName, event);
        
        for (int i = 1; i <= 100; i++) {
            kafkaTemplate.send(topicName, "key" + i,
                    new UserEvent("user" + i, "LOGIN"));
        }
        
    }
}

