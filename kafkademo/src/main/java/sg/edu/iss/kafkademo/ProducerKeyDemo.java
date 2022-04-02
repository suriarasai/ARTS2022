package sg.edu.iss.kafkademo;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sg.edu.iss.kafkademo.util.KafkaConstants;

public class ProducerKeyDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final Logger logger = LoggerFactory.getLogger(ProducerKeyDemo.class.getName());
		Properties producerProps = new Properties();
		producerProps.put("bootstrap.servers", KafkaConstants.BOOTSTRAPSERVERS);
		producerProps.put("key.serializer", KafkaConstants.KSERIALIZER);
		producerProps.put("value.serializer", KafkaConstants.KSERIALIZER);

		// create the producer
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(producerProps);

		for (int i = 0; i < 10; i++) {
			// create a producer record

			String topic = "first_topic";
			String value = "hello world " + Integer.toString(i);
			String key = "id_" + Integer.toString(i);

			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);

			logger.info("Key: " + key);
			// log the key
			// id_0 is going to partition 1
			// id_1 partition 0
			// id_2 partition 2
			// id_3 partition 0
			// id_4 partition 2
			// id_5 partition 2
			// id_6 partition 0
			// id_7 partition 2
			// id_8 partition 1
			// id_9 partition 2

			// send data - asynchronous
			producer.send(record, new Callback() {
				public void onCompletion(RecordMetadata recordMetadata, Exception e) {
					// executes every time a record is successfully sent or an exception is thrown
					if (e == null) {
						// the record was successfully sent
						logger.info("Received new metadata. \n" + "Topic:" + recordMetadata.topic() + "\n"
								+ "Partition: " + recordMetadata.partition() + "\n" + "Offset: "
								+ recordMetadata.offset() + "\n" + "Timestamp: " + recordMetadata.timestamp());
					} else {
						logger.error("Error while producing", e);
					}
				}
			}).get(); // block the .send() to make it synchronous - don't do this in production!
		}

		// flush data
		producer.flush();
		// flush and close producer
		producer.close();
	}
}
