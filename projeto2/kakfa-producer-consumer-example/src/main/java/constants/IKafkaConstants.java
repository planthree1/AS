package constants;

public interface IKafkaConstants {
	public static String KAFKA_BROKERS = "localhost:9092";
	
	public static Integer MESSAGE_COUNT=1000;
	
	public static String CLIENT_ID="client1";
	
	public static String TOPIC_NAME="CollectEntity";
	
	public static String TOPIC_NAME_BATCH="BatchTopic";
	
	public static String STATUS_OK="OK";
	
	public static String STATUS_KO="KO";
	
	public static String ALARM_FILE="ALARM.txt";
	
	public static String BATCH_FILE="BATCH.txt";
	
	public static String REPORT_FILE="REPORT.txt";
	
	public static String TOPIC_NAME_REPORT="ReportTopic";
	
	public static String TOPIC_NAME_ALARM="AlarmTopic";
	
	public static String GROUP_ID_CONFIG="consumerGroup10";
	
	public static Integer MAX_NO_MESSAGE_FOUND_COUNT=1000;
	
	public static String OFFSET_RESET_LATEST="latest";
	
	public static String OFFSET_RESET_EARLIER="earliest";
	
	public static Integer MAX_POLL_RECORDS=5;
}
