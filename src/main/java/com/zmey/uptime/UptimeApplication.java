package com.zmey.uptime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
public class UptimeApplication extends AbstractApplication {

	public static void main(String[] args) {	
		SpringApplication.run(UptimeApplication.class, args);
	}

}
