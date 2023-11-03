package com.example.bellintegrator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BellIntegratorApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCheckAuthentication() {
        ResponseEntity<String> response = restTemplate.getForEntity("/login/check?username=admin&password=password", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Authenticated", response.getBody());
    }

}
