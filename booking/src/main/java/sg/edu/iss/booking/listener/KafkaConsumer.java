package sg.edu.iss.booking.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import sg.edu.iss.booking.model.Booking;

@Service
public class KafkaConsumer {
	
    @Autowired
    private MongoOperations mongoOperations;

	@KafkaListener(topics = "booking", groupId = "group_id")
	public void consume(String message) {
		System.out.println("Consumed message: " + message);
	}

	@KafkaListener(topics = "booking_json", groupId = "group_json", containerFactory = "bookingKafkaListenerFactory")
	public void consumeJson(Booking booking) {
		System.out.println("Consumed JSON Message: " + booking);
		mongoOperations.save(booking, "bookings");
		System.out.println("persisted");
		
	}
}
