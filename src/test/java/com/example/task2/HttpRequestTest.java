package com.example.task2;

import com.example.task2.rest.RegisterRequest;
import com.example.task2.rest.RegisterResponse;
import com.example.task2.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void addUser() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .firstName("Gonenc")
                .lastName("Dogan")
                .password("123")
                .userName("gnnc").build();

        // build the request
        HttpEntity<RegisterRequest> entity = new HttpEntity<RegisterRequest>(request, null);

        ResponseEntity<RegisterResponse> registerResponseResponseEntity =
                this.restTemplate.
                        withBasicAuth("admin", "admin").
                        postForEntity("http://localhost:" + port + "/userservice/register", entity, RegisterResponse.class);

        assertThat(registerResponseResponseEntity.getBody().getUserName().equals("gnnc"));
    }
}
