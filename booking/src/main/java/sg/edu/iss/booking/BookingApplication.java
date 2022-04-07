package sg.edu.iss.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("sg.edu.iss.booking.*")
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

}
