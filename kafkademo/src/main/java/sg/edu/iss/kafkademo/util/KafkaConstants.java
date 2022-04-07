package sg.edu.iss.kafkademo.util;

public  class  KafkaConstants {
	
	 public static final String BOOTSTRAPSERVERS = "127.0.0.1:9092";
	 public static final String TOPIC = "first_topic";
	 public static final String KSERIALIZER="org.apache.kafka.common.serialization.StringSerializer";
	 public static final String KDSERIALIZER="org.apache.kafka.common.serialization.StringDeserializer";
}
