package sg.edu.iss.kafkademo;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import sg.edu.iss.kafkademo.util.KafkaConstants;

public class ProducerDemo {

	public static void main(String[] args) {
		Properties producerProps = new Properties();
		producerProps.put("bootstrap.servers", KafkaConstants.BOOTSTRAPSERVERS);
		producerProps.put("key.serializer", KafkaConstants.KSERIALIZER);
		producerProps.put("value.serializer", KafkaConstants.KSERIALIZER);
		producerProps.put("acks", "all");
		producerProps.put("retries", 1);
		producerProps.put("batch.size", 20000);
		producerProps.put("linger.ms", 1);
		producerProps.put("buffer.memory", 24568545);
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(producerProps);

		for (int i = 0; i < 200; i++) {
			ProducerRecord data = new ProducerRecord<String, String>("test1", "Hello this is record " + i);
			Future<RecordMetadata> recordMetadata = producer.send(data);
		}
		producer.close();

	}

}
