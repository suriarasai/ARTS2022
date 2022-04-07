package sg.edu.iss.booking.model;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	private int cid;
	private String cname;
	private String cphone;
	private Double plat;
	private Double plon;
	

}
