package com.novatronic.pscabas.gt.webcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GtWebcoreApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GtWebcoreApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GtWebcoreApplication.class, args);
		System.out.println("success");
	}

}
