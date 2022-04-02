package sg.edu.iss.kafkademo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sg.edu.iss.kafkademo.util.KafkaConstants;

public class ConsumerDemo {
	
	public static void main(String[] args) {
		
		Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());

		String topic = "test1";
		List<String> topicList = new ArrayList();
		topicList.add(topic);
		Properties consumerProperties = new Properties();
		consumerProperties.put("bootstrap.servers", KafkaConstants.BOOTSTRAPSERVERS);
		consumerProperties.put("group.id", "Demo_Group");
		consumerProperties.put("key.deserializer", KafkaConstants.KDSERIALIZER);
		consumerProperties.put("value.deserializer", KafkaConstants.KDSERIALIZER);

		consumerProperties.put("enable.auto.commit", "true");
		consumerProperties.put("auto.commit.interval.ms", "1000");
		consumerProperties.put("session.timeout.ms", "30000");

		KafkaConsumer<String, String> demoKafkaConsumer = new KafkaConsumer<String, String>(consumerProperties);

		demoKafkaConsumer.subscribe(topicList);
		logger.info("Subscribed to topic " + topic);
		int i = 0;
		try {
			while (true) {
				ConsumerRecords<String, String> records = demoKafkaConsumer.poll(500);
				for (ConsumerRecord<String, String> record : records)
					logger.info("offset = " + record.offset() + "key =" + record.key() + "value =" + record.value());

				// TODO : Do processing for data here
				demoKafkaConsumer.commitAsync(new OffsetCommitCallback() {
					public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {

					}
				});

			}
		} catch (Exception ex) {
			// TODO : Log Exception Here
		} finally {
			try {
				demoKafkaConsumer.commitSync();

			} finally {
				demoKafkaConsumer.close();
			}
		}
	}

}
