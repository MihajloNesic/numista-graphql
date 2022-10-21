package io.gitlab.mihajlonesic.numistagraphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class NumistaGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumistaGraphqlApplication.class, args);
	}

	@PostConstruct
	public void postInit() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
