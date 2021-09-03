package com.rugbyaholic.communityPG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CommunityRogramApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CommunityRogramApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CommunityRogramApplication.class);
	}

}
