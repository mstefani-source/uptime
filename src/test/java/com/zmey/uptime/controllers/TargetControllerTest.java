package com.zmey.uptime.controllers;

import java.util.ArrayList;
import java.util.List;

import com.zmey.uptime.entities.Target;
import com.zmey.uptime.repositories.TargetRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
class TargetControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	TargetRepository targetRepository;

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

	@BeforeEach
	void setUp() {

		RestAssured.baseURI = "http://localhost:" + port;
		targetRepository.deleteAll();

		List<Target> targets = new ArrayList();

		targets.add(new Target("Africa", "http://durban.za", "SA", "beauteful beaches of SA"));
		targets.add(new Target("Africa", "http://pretoria.za", "SA", "second capital of SA"));
		targets.add(new Target("Africa", "http://capetown.za", "SA", "third capital of SA"));
		targets.add(new Target("Africa", "http://johannesburg.za", "SA", "first capital of SA"));
		targets.add(new Target("Africa", "http://petermaritzburg.za", "SA", "city of SA"));
		targets.add(new Target("Africa", "http://port-elizabet.za", "SA", "city of SA"));
		targets.add(new Target("Africa", "http://benoni.za", "SA", "city of SA"));
		targets.add(new Target("Africa", "http://soueto.za", "SA", "city of SA"));
		targets.add(new Target("Africa", "http://welkom.za", "SA", "city of SA"));
		targets.add(new Target("Africa", "http://boxburg.za", "SA", "city of SA"));

		targetRepository.saveAll(targets);
	}

	@Test
	@DisplayName("get targets")
	void testFindAll() {
		given().contentType(ContentType.JSON).when().get("/targets").then().statusCode(200)
				.contentType(ContentType.JSON).body(".", hasSize(10));
	}

	@Test
	@DisplayName("create target")
	void testCreateTarget() {
		given().contentType(ContentType.JSON).body(
				"{ \"customer\": \"Europe\", \"url\": \"http://berlin.de\", \"name\": \"DE\", \"description\": \"capital of Germany\" }")
				.when().post("/targets").then().statusCode(201) // expecting HTTP 201 Created
				.contentType(ContentType.JSON).body(".", hasKey("id")); // expecting JSON response content
	}

	// @Test
	// @DisplayName("delete target")
	// void testDeleteTarget() {
	// Long id = 2L; // replace with a valid ID
	// given()
	// .pathParam("id", id)
	// .when().delete("/targets/{id}").then().statusCode(204); // expecting HTTP 204
	// No
	// }

}
