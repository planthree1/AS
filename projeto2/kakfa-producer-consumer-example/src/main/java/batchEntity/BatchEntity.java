package batchEntity;

import java.io.IOException;
import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import constants.IKafkaConstants;
import consumer.ConsumerCreator;
import writers.WriteFile;

public class BatchEntity {
	
	// Init GUI variable
	public static BatchEntityFrame batchFrame = new BatchEntityFrame();

	public static void main(String[] args) {		
		batchFrame.setVisible(true);
		batchFrame.setLocationRelativeTo(null);
		// runs consumer
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
				String[] output = record.value().split("\\|");
				
				try {
					WriteFile.writeToFile(record.value(), IKafkaConstants.BATCH_FILE);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				String output_msg = "Matricula: " + output[1] + " | TimeStamp: " + output[2] + " | Partição: " + record.partition() + " | Offset: " + record.offset() ;
				
				String actualText = batchFrame.getTxtOutput();
				String newText = "" + actualText + "\n" + output_msg + "";
				batchFrame.setTxtOutput(newText);				
			});
			consumer.commitAsync();
		}
		consumer.close();
	}

}
