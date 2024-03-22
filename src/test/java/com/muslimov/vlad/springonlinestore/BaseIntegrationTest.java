package com.muslimov.vlad.springonlinestore;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@Transactional
@SpringBootTest()
@AutoConfigureMockMvc
public class BaseIntegrationTest {

	@Autowired
	protected MockMvc mockMvc;

	@Container
	public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.1-alpine");

	@DynamicPropertySource
	static void postgresProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}

	@BeforeAll
	public static void startContainer() {
		postgres.start();
	}
}