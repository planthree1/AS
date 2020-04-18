package com.gaurav.kafka.consumer;

import java.io.IOException;
import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.gaurav.kafka.constants.IKafkaConstants;

import writers.write_file;

public class AlarmEntity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		alarmEntityConsumer();
	}
	
	static void alarmEntityConsumer() {
		System.out.println("hereãsdsa");
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer();
		consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_NAME_ALARM));

		int noMessageToFetch = 0;
		
		while (true) {
			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
			if (consumerRecords.count() == 0) {
				noMessageToFetch++;
				if (noMessageToFetch > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
					break;
				else
					continue;
			}

			System.out.println("here");
			consumerRecords.forEach(record -> {
				String[] output = record.value().split("\\|");
				int velocity = Integer.parseInt(output[4]);
				if (velocity > 120) {
					System.out.println("ko");
				} else {
					System.out.println("ok");
				}
				
			});
			consumer.commitAsync();
		}
		consumer.close();
	}

}
