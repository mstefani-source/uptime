package com.zmey.uptime.controllers;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;

import com.zaxxer.hikari.HikariDataSource;
import com.zmey.uptime.entities.Target;
import jakarta.activation.DataSource;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.zmey.uptime.entities.Customer;
import com.zmey.uptime.repositories.CustomerRepository;
import com.zmey.uptime.repositories.TargetRepository;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.http.ContentType;

import java.util.Comparator;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
class TargetControllerTest {
    private static final Logger logger = LogManager.getLogger(TargetControllerTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TargetRepository targetRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @BeforeAll
    static void beforeAll() {
        logger.info("BeforeAll OK");
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        logger.info("AfterAll OK");
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        targetRepository.deleteAll();
        Customer customer = new Customer();
        customer.setName("Ivanov");
        customerRepository.save(customer);

        List<Target> targets = List.of(
                new Target(customer, "http://durban.za", "SA", "beauteful beaches of SA"),
                new Target(customer, "http://pretoria.za", "SA", "second capital of SA"),
                new Target(customer, "http://capetown.za", "SA", "third capital of SA"),
                new Target(customer, "http://johannesburg.za", "SA", "first capital of SA"),
                new Target(customer, "http://petermaritzburg.za", "SA", "city of SA"),
                new Target(customer, "http://port-elizabet.za", "SA", "city of SA"),
                new Target(customer, "http://benoni.za", "SA", "city of SA"),
                new Target(customer, "http://soueto.za", "SA", "city of SA"),
                new Target(customer, "http://welkom.za", "SA", "city of SA"),
                new Target(customer, "http://boxburg.za", "SA", "city of SA"));
        targetRepository.saveAll(targets);
        logger.info("BeforeEach done!");
    }

    @AfterEach
    public void afterEachTest() throws InterruptedException {
        Thread.sleep(1000); // Пауза в миллисекундах (например, 1 секунда)
    }

    @Test
    @DisplayName("findAllTargets should return all targets")
    void findAllTargetsTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/targets")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(".", hasSize(10));
    }

    @Test
    @DisplayName("findAllTargets should return empty array if no targets")
    void findAllTargetsIfEmptyTest() {
        targetRepository.deleteAll();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/targets")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(".", hasSize(0));
    }

    @Test
    @DisplayName("createTarget should create a new target")
    void createTargetTest() {
        String payload = """
                   	{
                	"customerId": 1,
                	"url": "http://berlin.de",
                	"name": "DE",
                	"description": "capital of Germany"
                }
                """;

        given().contentType(ContentType.JSON)
                .body(payload).when().post("/targets").then()
                .statusCode(201)
                .contentType(ContentType.JSON).body(".",
                        hasKey("id"), ".",
                        hasKey("customer"), ".",
                        hasEntry("url", "http://berlin.de"), ".",
                        hasEntry("name", "DE"), ".",
                        hasEntry("description", "capital of Germany"));
    }

    @Test
    @DisplayName("removeTarget should delete a target if exists")
    void removeTargetTest() {
        // Given
        assertEquals(10, targetRepository.findAll().size());

        Customer customer = new Customer();
        customer.setName("Ivanov");
        customerRepository.save(customer);

        Target target = new Target(customer, "http://china.cn", "CN", "city of China");
        Target savedTarget = targetRepository.save(target);

        assertEquals(11, targetRepository.findAll().size());


        // When
        given()
                .pathParam("id", savedTarget.getId())
                .when()
                .delete("/targets/{id}")
                .then()
                .statusCode(204);

        // Then
        assertEquals(10, targetRepository.findAll().size());
    }

    @Test
    @DisplayName("removeTarget should return 404 if target not exists")
    void removeNotExistsTargetTest() {
        Target targetWithMaxId = targetRepository.findAll().stream()
                .max(Comparator.comparing(Target::getId))
                .orElseThrow(NoSuchElementException::new);

        Long id = targetWithMaxId.getId() + 1;

        given()
                .pathParam("id", id)
                .when()
                .delete("/targets/{id}")
                .then()
                .statusCode(404);
    }
}