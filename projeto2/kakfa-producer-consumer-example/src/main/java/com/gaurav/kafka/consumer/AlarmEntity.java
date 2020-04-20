package com.gaurav.kafka.consumer;

import java.io.IOException;
import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.gaurav.kafka.constants.IKafkaConstants;

import writers.write_file;

public class AlarmEntity {
	
	// Init Alarm GUI variable
	public static AlarmEntityFrame alarmFrame = new AlarmEntityFrame();

	public static void main(String[] args) {
		alarmFrame.setVisible(true);
		alarmFrame.setLocationRelativeTo(null);
		
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
				
				System.out.println(record.toString());
				String[] output = record.value().split("\\|");
				int velocity = Integer.parseInt(output[4]);

				String status = "";
				if (velocity > 120) {
					status = "ko";
					System.out.println("ko");
				} else {
					status = "ok";
					System.out.println("ok");
				}
				
				// Send message to Alarm GUI
				String output_msg = record.key() + " - " + record.value() + " - " + record.partition() + " - " + record.offset() + " - status: " + status;
				String actualText = alarmFrame.getTxtOutput();
				String newText = "" + actualText + "\n" + output_msg + "";
				alarmFrame.setTxtOutput(newText);
			});
			consumer.commitAsync();
		}
		consumer.close();
	}
}
