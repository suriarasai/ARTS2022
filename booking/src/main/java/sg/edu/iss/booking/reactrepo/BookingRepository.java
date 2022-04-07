package sg.edu.iss.booking.reactrepo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import sg.edu.iss.booking.model.Booking;


public interface BookingRepository extends ReactiveMongoRepository<Booking, Integer> {
}
