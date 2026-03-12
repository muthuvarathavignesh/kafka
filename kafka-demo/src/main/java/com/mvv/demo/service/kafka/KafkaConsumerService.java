package com.mvv.demo.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.mvv.demo.dto.UserEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerService {

	@KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume1(UserEvent event, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, Acknowledgment ack) {

		try {

			System.out.println("Received Event -- Group demo-group-1  -> UserId: " + event.getUserId() + ", Action: "
					+ event.getAction() + ", Partition: " + partition);

			ack.acknowledge();

			System.out.println("Offset committed successfully.");

		} catch (Exception e) {

			System.out.println("Error occurred. Offset NOT committed.");
			// Do NOT call acknowledge()
		}

	}

	/*
	 * @KafkaListener(topics = "${app.kafka.topic}", groupId = "demo-group-2")
	 * public void consume2(UserEvent event, Acknowledgment ack) {
	 * 
	 * try {
	 * 
	 * System.out.println("Received Event -- Group demo-group-2 -> UserId: " +
	 * event.getUserId() + ", Action: " + event.getAction());
	 * 
	 * ack.acknowledge();
	 * 
	 * System.out.println("Offset committed successfully.");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.out.println("Error occurred. Offset NOT committed."); // Do NOT call
	 * acknowledge() }
	 * 
	 * }
	 * 
	 * @KafkaListener(topics = "${app.kafka.topic}", groupId = "demo-group-3")
	 * public void consume3(UserEvent event, Acknowledgment ack) {
	 * 
	 * try {
	 * 
	 * System.out.println("Received Event -- Group demo-group-3 -> UserId: " +
	 * event.getUserId() + ", Action: " + event.getAction());
	 * 
	 * ack.acknowledge();
	 * 
	 * System.out.println("Offset committed successfully.");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.out.println("Error occurred. Offset NOT committed."); // Do NOT call
	 * acknowledge() }
	 * 
	 * }
	 */
}
