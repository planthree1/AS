package com.gaurav.kafka;

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.gaurav.kafka.constants.IKafkaConstants;
import com.gaurav.kafka.consumer.ConsumerCreator;
import com.gaurav.kafka.producer.ProducerCreator;

import kafka.*;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import readers.read_file;

public class App {
	public static void main(String[] args) {
		
		ArrayList<String> car_info = read_file.readFile();
//		ArrayList<ArrayList<String>> result = splitInfo(car_info);
		
		runProducer(splitInfo(car_info));
	}
	
	static ArrayList<ArrayList<String>> splitInfo(ArrayList<String> car_info) {
		
		ArrayList<String> AlarmTopic = new ArrayList<String>();
		ArrayList<String> ReportTopic = new ArrayList<String>();
		ArrayList<String> BatchTopic = new ArrayList<String>();

		int count = 0;
		
		for (String info : car_info) 
		{ 
			
		    String[] output = info.split("|");
		    
		    for (int i = 0; i < output.length ; i++ ) {
		    	if (output[i].charAt(0) == '|') {
		    		count++;
		    	}
		    }
		    
		    if (count == 4) {
		    	//System.out.println("its a Heartbeat");
		    	BatchTopic.add(info);
		    } else {
		    	if (output[output.length - 2].charAt(0) == 'K' || output[output.length - 2].charAt(0) == 'O') {
		    		//System.out.println("its a status");
		    		ReportTopic.add(info);
		    		
		    	} else {
		    		//System.out.println("its a velocity");
		    		AlarmTopic.add(info);
		    	}
		    }
		    
		    count = 0;
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
		
		
		int a = 0;
	    for (String info: AlarmTopic) {
	    	
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME_ALARM, info);

			System.out.println("record: " + record.toString());
			
			try {
				
				RecordMetadata metadata = producer.send(record).get();
				System.out.println("Record sent with key " + a + " to partition " + metadata.partition()
						+ " with offset " + metadata.offset());
				
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}
	    	a++;
	    }
	    
	    int b = 0;
	    for (String info: ReportTopic) {
				
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME_REPORT, info);

			System.out.println("record: " + record.toString());
			
			try {
				
				RecordMetadata metadata = producer.send(record).get();
				System.out.println("Record sent with key " + b + " to partition " + metadata.partition()
						+ " with offset " + metadata.offset());
				
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}	
			
	    	b++;
	    }

	    int c = 0;
		for (String info: BatchTopic) {
			
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME_BATCH, info);

			System.out.println("record: " + record.toString());
			
			try {
				
				RecordMetadata metadata = producer.send(record).get();
				System.out.println("Record sent with key " + c + " to partition " + metadata.partition()
						+ " with offset " + metadata.offset());
				
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}	
			c++;
		}

	}
}
