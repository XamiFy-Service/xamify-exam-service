package com.xamify.exam.restconnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthClient {

    @Autowired
    private RestTemplate restTemplate;

    String baseUrl = "http://localhost:8080";

    public String validateToken(String token) {
        // URL of the AuthController in User Service
        String authUrl = baseUrl + "/auth";

        // Step 1: Create HTTP headers object to pass headers
        HttpHeaders headers = new HttpHeaders();

        // Step 2: Add the Authorization header with the token
        // Important: This must match how AuthController expects it ("Bearer <token>")
        headers.set("Authorization",token);

        // Step 3: Create an HttpEntity that wraps the headers (no body needed for GET)
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Step 4: Make the REST call using RestTemplate.exchange()
            // - Pass the URL
            // - Use GET method
            // - Include the entity (which has the headers)
            // - Expect a response of type String (the role: "STUDENT", "ADMIN", or "INVALID")
            ResponseEntity<String> response = restTemplate.exchange(
                    authUrl,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            // Step 5: Return the body of the response (the user role)
            return response.getBody();
        } catch (Exception e) {
            // Step 6: If anything goes wrong (service down, invalid response), return INVALID
            return "INVALID";
        }
    }


}
