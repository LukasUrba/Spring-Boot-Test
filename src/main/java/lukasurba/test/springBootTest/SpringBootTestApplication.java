package lukasurba.test.springBootTest;

import lukasurba.test.springBootTest.run.Location;
import lukasurba.test.springBootTest.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringBootTestApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootTestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);

	}

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			Run run = new Run(1,"My Run", LocalDateTime.now().minusMinutes(30),LocalDateTime.now(),4, Location.OUTDOOR);
//			log.info("Run: " + run);
//		};
//	}

}
