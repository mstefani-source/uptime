package com.zmey.uptime.controllers;

import com.zmey.uptime.entities.Customer;
import com.zmey.uptime.entities.Target;
import com.zmey.uptime.entities.enums.Role;
import com.zmey.uptime.repositories.CustomerRepository;
import com.zmey.uptime.repositories.TargetRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

import java.util.List;
import java.util.NoSuchElementException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
class CustomerControllerTest {
    private static final Logger logger = LogManager.getLogger(CustomerControllerTest.class);
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine").withExposedPorts(5432);
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TargetRepository targetRepository;
    @LocalServerPort
    private int port;

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
    void setUp(TestInfo testInfo) {
        RestAssured.baseURI = "http://localhost:" + port;

        Customer customer = new Customer();
        customer.setName("Ivanov");
        customer.setEmail("test@gmail.com");
        customer.setPassword("password");

        customerRepository.save(customer);

        List<Target> targets = List.of(
                new Target(customer, "http://durban.za", "SA", "beautiful beaches of SA", true),
                new Target(customer, "http://pretoria.za", "SA", "second capital of SA", true),
                new Target(customer, "http://capetown.za", "SA", "third capital of SA", true),
                new Target(customer, "http://johannesburg.za", "SA", "first capital of SA", true),
                new Target(customer, "http://petermaritzburg.za", "SA", "city of SA", true),
                new Target(customer, "http://port-elizabet.za", "SA", "city of SA", true),
                new Target(customer, "http://benoni.za", "SA", "city of SA", true),
                new Target(customer, "http://soueto.za", "SA", "city of SA", true),
                new Target(customer, "http://welkom.za", "SA", "city of SA", true),
                new Target(customer, "http://boxburg.za", "SA", "city of SA", true));
        targetRepository.saveAll(targets);
        logger.info("Expected {}", testInfo.getDisplayName());
    }

    @AfterEach
    void shutDown() {
        targetRepository.deleteAll();
        customerRepository.deleteAll();
    }


    @Test
    @DisplayName("Should create customer")
    void createCustomer() {

        String payload = """
                {
                	"name": "Petrov",
                	"email": "test@ya.ru",
                	"password": "pw",
                	"role": "ROLE_ADMIN"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(payload)
                .post("/customers")
                .then().statusCode(201)
                .contentType(ContentType.JSON)
                .body(".", hasKey("id"),
                        ".", hasEntry("name", "Petrov")
                );
    }

    @Test
    @DisplayName("Should return all customers")
    void findAllCustomers(){}

    @Test
    @DisplayName("Should delete customer")
    void delCustomers(){}

    @Test
    @DisplayName("Should return empty customer")
    void getCustomers(){}


    @Test
    @DisplayName("Should return 500 if Customer having a Target")
    void removeCustomerWithTargets() {
        Customer customer = customerRepository.findAll().stream().findFirst().orElseThrow(NoSuchElementException::new);
        List<Target> targets = targetRepository.findAll();
        assertEquals(10, targets.size());
        assertEquals(1, customerRepository.findAll().size());

        given()
                .pathParam("id", customer.getId())
                .when()
                .delete("/customers/{id}")
                .then()
                .statusCode(500);
        logger.info("OK");
    }
}