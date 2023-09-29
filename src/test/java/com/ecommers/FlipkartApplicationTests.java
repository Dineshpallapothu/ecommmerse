package com.ecommers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FlipkartApplicationTests {

	@LocalServerPort
	private  int port;
	private String baseUrl="http://localhost";
	@Autowired
	private RestTemplate restTemplate;

	@BeforeAll
	public static void init(){}
	@Test
	void contextLoads() {
	}

}
