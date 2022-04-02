package sg.edu.iss.kafkademo;

public class SimpleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getenv().forEach((k, v) -> {
		    System.out.println(k + ":" + v);
		});
	}

}
