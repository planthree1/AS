package alarmEntity;

import java.io.IOException;
import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import constants.IKafkaConstants;
import consumer.ConsumerCreator;
import consumer.ConsumerCreatorAlarm;
import writers.WriteFile;

public class AlarmEntity {
	
	// Init Alarm GUI variable
	public static AlarmEntityFrame alarmFrame = new AlarmEntityFrame();

	public static void main(String[] args) {
		alarmFrame.setVisible(true);
		alarmFrame.setLocationRelativeTo(null);
		// runs consumer
		alarmEntityConsumer();
	}
	
	static void alarmEntityConsumer() {
		
		Consumer<Long, String> consumer = ConsumerCreatorAlarm.createConsumer();
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

			consumerRecords.forEach(record -> {
				
				String[] output = record.value().split("\\|");
				int velocity = Integer.parseInt(output[4]);
				String status = "";
				// checks if the velocity is ok or ko and saves it on the ALARM.TXT FILE
				try {
					if (velocity > 120) {
						status = "ko";
						WriteFile.writeToFile(record.value() + status, IKafkaConstants.ALARM_FILE);
					} else {
						status = "ok";
						WriteFile.writeToFile(record.value() + status, IKafkaConstants.ALARM_FILE);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				// Send message to Alarm GUI
				//String output_msg = record.key() + " - " + record.value() + " - " + record.partition() + " - " + record.offset() + " - status: " + status;
				String output_msg = "Matricula: " + output[1] + " | TimeStamp: " + output[2] + " | Velocidade: " + output[4] + " | Partição: " + record.partition() + " | Offset: " + record.offset() + " | Status: " + status;
				String actualText = alarmFrame.getTxtOutput();
				String newText = "" + actualText + "\n" + output_msg + "";
				alarmFrame.setTxtOutput(newText);
			});
			consumer.commitAsync();
		}
		consumer.close();
	}
}
