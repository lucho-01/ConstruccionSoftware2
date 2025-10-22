package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.adapter.in.client.AdministratorClient;
import app.adapter.in.client.DoctorsClient;
import app.adapter.in.client.HumanResourseClient;
import app.adapter.in.client.NursesClient;

@SpringBootApplication
public class Software2Application implements CommandLineRunner{
	@Autowired
	private HumanResourseClient humanResourseClient;
	@Autowired
	private DoctorsClient doctorsClient;
	@Autowired
	private NursesClient nursesClient;
	@Autowired
	private AdministratorClient administratorClient;

	public static void main(String[] args) {
		SpringApplication.run(Software2Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		administratorClient.session();
	}

}
