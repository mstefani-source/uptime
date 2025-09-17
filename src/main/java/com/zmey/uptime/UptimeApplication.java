package com.zmey.uptime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UptimeApplication extends AbstractApplication {

	public static void main(String[] args) {	
		SpringApplication app = new SpringApplication(UptimeApplication.class);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
	}

}
