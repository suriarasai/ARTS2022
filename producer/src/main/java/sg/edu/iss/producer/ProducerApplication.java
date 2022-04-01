package sg.edu.iss.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
	
	@Bean
	public NewTopic topic() {
	   return TopicBuilder.name("springtopic").partitions(2).replicas(1).build();	
	}
	
	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> template) { 
		
		return arg -> {
			
			template.send("springtopic", "another test");
		};
	}
	

}
