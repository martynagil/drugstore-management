package com.github.martynagil.drugstoremanagement.config;

import com.github.martynagil.drugstoremanagement.service.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class TimeConfig {

	@Bean
	Clock clock() {
		return () -> LocalDateTime.now();
	}
}
