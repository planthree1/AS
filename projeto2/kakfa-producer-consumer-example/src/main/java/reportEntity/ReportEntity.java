package reportEntity;

import java.io.IOException;
import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import constants.IKafkaConstants;
import consumer.ConsumerCreator;
import writers.WriteFile;

public class ReportEntity {
	
	// Init GUI variable
	public static ReportEntityFrame reportFrame = new ReportEntityFrame();

	public static void main(String[] args) {
		reportFrame.setVisible(true);
		reportFrame.setLocationRelativeTo(null);
		// runs consumer 
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
				
				String[] output = record.value().split("\\|");
				
				try {
					WriteFile.writeToFile(record.value(), IKafkaConstants.REPORT_FILE);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				// Send message to GUI
				String output_msg = "Matricula: " + output[1] + " | TimeStamp: " + output[2] + " | Partição: " + record.partition() + " | Offset: " + record.offset() ;
								
				String actualText = reportFrame.getTxtOutput();
				String newText = "" + actualText + "\n" + output_msg + "";
				reportFrame.setTxtOutput(newText);
			});
			consumer.commitAsync();
		}
		consumer.close();
	}
}
