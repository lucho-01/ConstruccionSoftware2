package app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.adapter.in.client.DoctorsClient;

@SpringBootApplication
public class Software2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Software2Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		DoctorsClient doctorsClient = new DoctorsClient();
		doctorsClient.session();
	}

}
