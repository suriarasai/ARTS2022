package sg.edu.iss.booking.controller;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import sg.edu.iss.booking.model.Booking;

@RestController
public class BookingController {

	private final ReactiveMongoTemplate mongoTemplate;

	public BookingController(ReactiveMongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@GetMapping(value = "/bookings", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Booking> bookings() {
		return mongoTemplate.tail(new Query(), Booking.class).share();
	}
}
