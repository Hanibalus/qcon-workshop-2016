package com.example;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {
	
	@RequestMapping("/")
	public Map<String,Object> home() {
		return Collections.singletonMap("msg", "Hello QCon");
	}

	@RequestMapping("/hello")
	public Map<String,Object> hello() {
		return Collections.singletonMap("msg", "Hello To Everyone");
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
