package sg.edu.iss.tempdemo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TempproducerApplication {

	LinkedList<String> tempreadings = new LinkedList<String>(List.of("26.0", "26.5", "27.0", "27.5", "29.0", "28.5",
			"29.0", "29.5", "30.0", "30.5", "31.0", "31.5", "32.0", "32.5", "33.0", "33.5", "33.0", "32.5", "32.0",
			"31.5", "31.0", "30.5", "30.0", "29.5", "29.0", "28.5", "28.0", "27.5", "27.0", "26.5", "26.0"));

	public static void main(String[] args) {
		SpringApplication.run(TempproducerApplication.class, args);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("livetemperature").partitions(2).replicas(1).build();
	}

	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> template) { 

		return arg -> {
			
			for (String tstring : tempreadings) {
				template.send("livetemperature", tstring);
				TimeUnit.SECONDS.sleep(3);
				log.info("Current Temperature: {}", tstring);
			} 
			
		};
	}

	
}
