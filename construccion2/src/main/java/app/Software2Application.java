package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.adapter.in.client.DoctorsClient;
import app.adapter.in.client.HumanResourseClient;

@SpringBootApplication
public class Software2Application implements CommandLineRunner{
	@Autowired
	private HumanResourseClient humanResourseClient;
	@Autowired
	private DoctorsClient doctorsClient;

	public static void main(String[] args) {
		SpringApplication.run(Software2Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		humanResourseClient.session();
	}

}
