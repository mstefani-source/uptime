package com.zmey.uptime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UptimeApplication extends AbstractApplication {

	public static void main(String[] args) {	
		SpringApplication.run(UptimeApplication.class, args);
	}

}
