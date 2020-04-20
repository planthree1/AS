package collectEntity;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import constants.IKafkaConstants;

public class ProducerCreatorBatch {

	public static Producer<Long, String> createProducer() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.ACKS_CONFIG, "0");
		props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "1");
		
		//this may cause the order of the messages to be changed since one message may fail and the next oone takes that message offset
		props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "50");
		
		// ACKS_CONFIG  0 means that the client does not require any acknowledgement back, if one consumer fails and there might be loss messages
		// when the messages are being splited to adjacent consumers
		props.put(ProducerConfig.ACKS_CONFIG, "0");
		
		return new KafkaProducer<>(props);
	}
}