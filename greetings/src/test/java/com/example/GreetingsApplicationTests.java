package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GreetingsApplication.class)
@WebIntegrationTest("server.port=0")
public class GreetingsApplicationTests {

	@Value("${local.server.port}")
	private int port;

	@Test
	public void contextLoads() {
		System.err.println(port);
	}

}
