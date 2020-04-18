package com.gaurav.kafka.consumer;

import java.io.IOException;
import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.record.Record;

import com.gaurav.kafka.constants.IKafkaConstants;
import writers.*;

public class BatchEntity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		batchEntityConsumer();
	}
	
	static void batchEntityConsumer() {
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer();
		consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_NAME_BATCH));
		
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
					write_file.WriteFile(record.value(), "BATCH.txt");
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
			});
			
			
			consumer.commitAsync();
		}
		consumer.close();
	}

}
