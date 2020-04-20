package collectEntity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import constants.IKafkaConstants;
import readers.ReadFile;

public class CollectEntity {
	public static void main(String[] args) {
		// Reads the info from the car.txt
		ArrayList<String> car_info = ReadFile.readFile();
		// Splits the info gathered in car_info to several array lists so the producer know's where to send the messages
		runProducer(splitInfo(car_info));
	}
	
	static ArrayList<ArrayList<String>> splitInfo(ArrayList<String> car_info) {
		
		ArrayList<String> AlarmTopic = new ArrayList<String>();
		ArrayList<String> ReportTopic = new ArrayList<String>();
		ArrayList<String> BatchTopic = new ArrayList<String>();
		
		// runs every string of into and splits it between the ArrayLists AlarmTopic, ReportTopic and BatchTopic
		for (String info : car_info) 
		{ 
			
		    String[] output = info.split("\\|");
		    
		    if (output.length == 4) {
		    	// Its a Heartbeat
		    	BatchTopic.add(info);
		    } else {
		    	
		    	if (output[4].equals(IKafkaConstants.STATUS_KO) || output[4].equals(IKafkaConstants.STATUS_OK)) {
		    		// Its a status
		    		ReportTopic.add(info);
		    		
		    	} else {
		    		// Its a velocity"
		    		AlarmTopic.add(info);
		    	}
		    }

		}
		
		//returns the results that will be posted to each topic
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		result.add(AlarmTopic);
		result.add(ReportTopic);
		result.add(BatchTopic);
		
		return result;
	}



	static void runProducer(ArrayList<ArrayList<String>> result) {
		
		Producer<Long, String> producer = ProducerCreator.createProducer();
		ArrayList<String> AlarmTopic = result.get(0);
		ArrayList<String> ReportTopic = result.get(1);
		ArrayList<String> BatchTopic = result.get(2);
		
		
		// PUBLISHES messages to AlarmTopic

	    for (String info: AlarmTopic) {
	    	
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME_ALARM, info);
		
			try {
				
				producer.send(record).get();
				System.out.println("message published in : " + IKafkaConstants.TOPIC_NAME_ALARM);
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}
	    }
	    
	    // PUBLISHES messages to ReportTopic

	    for (String info: ReportTopic) {
				
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME_REPORT, info);

			try {
				
				producer.send(record).get();
				System.out.println("message published in : " + IKafkaConstants.TOPIC_NAME_REPORT);
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}	

	    }

	    // PUBLISHES messages to BatchTopic

		for (String info: BatchTopic) {
			
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME_BATCH, info);

			try {
				
				producer.send(record).get();
				System.out.println("message published in : " + IKafkaConstants.TOPIC_NAME_BATCH);
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}	

		}

	}
}
