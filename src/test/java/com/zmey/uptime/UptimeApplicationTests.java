package com.zmey.uptime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
class UptimeApplicationTests {

	@LocalServerPort
	private Integer port;

	// @Autowired
	// TargetRepository targetRepository;

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

	// start container
	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	// stop container
	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	// @BeforeEach
	// void setUp() {
	// RestAssured.baseURI = "http://localhost:" + port;
	// targetRepository.deleteAll();

	// Target t0 = new Target("huzzah", "http://durban.za", "SA", "beauteful beaches
	// of SA");
	// Target t1 = new Target("huzzah", "http://pretoria.za", "SA", "second capital
	// of SA");
	// Target t2 = new Target("huzzah", "http://capetown.za", "SA", "third capital
	// of SA");
	// Target t3 = new Target("huzzah", "http://johannesburg.za", "SA", "first
	// capital of SA");
	// Target t4 = new Target("huzzah", "http://petermaritzburg.za", "SA", "city of
	// SA");
	// Target t5 = new Target("huzzah", "http://port-elizabet.za", "SA", "city of
	// SA");
	// Target t6 = new Target("huzzah", "http://benoni.za", "SA", "city of SA");
	// Target t7 = new Target("huzzah", "http://soueto.za", "SA", "city of SA");
	// Target t8 = new Target("huzzah", "http://welkom.za", "SA", "city of SA");
	// Target t9 = new Target("huzzah", "http://boxburg.za", "SA", "city of SA");

	// targetRepository.saveAll(List.of(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9));
	// }

	@Test
	void contextLoads() {

	}

	// @Test
	// void testFindAll() {
	// given()
	// .contentType(ContentType.JSON)
	// .when()
	// .get("/targets")
	// .then()
	// .statusCode(200)
	// .contentType(ContentType.JSON)
	// .body(".", hasSize(4));
	// }

}
