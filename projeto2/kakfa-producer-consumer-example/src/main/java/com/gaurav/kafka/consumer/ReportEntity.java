package com.gaurav.kafka.consumer;

import java.io.IOException;
import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.gaurav.kafka.constants.IKafkaConstants;

import writers.write_file;

public class ReportEntity {
	
	// Init GUI variable
	public static ReportEntityFrame reportFrame = new ReportEntityFrame();

	public static void main(String[] args) {
		reportFrame.setVisible(true);
		reportFrame.setLocationRelativeTo(null);
		
		reportEntityConsumer();
	}
	
	static void reportEntityConsumer() {
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer();
		consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_NAME_REPORT));
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

			
			consumerRecords.forEach(record -> {
				try {
					write_file.WriteFile(record.value(), "REPORT.txt");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				System.out.println("Record Key " + record.key());
				System.out.println("Record value " + record.value());
				System.out.println("Record partition " + record.partition());
				System.out.println("Record offset " + record.offset());
				*/
				
				String output = record.key() + " - " + record.value() + " - " + record.partition() + " - " + record.offset();
				String actualText = reportFrame.getTxtOutput();
				String newText = "" + actualText + "\n" + output + "";
				reportFrame.setTxtOutput(newText);
			});
			consumer.commitAsync();
		}
		consumer.close();
	}
}
