package com.macadribacks.usage_service.client;

import com.macadribacks.usage_service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    private final String baseURL;

    public  UserClient(@Value("${user.service.url}") String baseURL) {
        this.restTemplate = new RestTemplate();
        this.baseURL =baseURL;
    }

    public UserDTO getUserById(Long userId) {
        String url = UriComponentsBuilder
                .fromUriString(baseURL)
                .path("/{userId}")
                .buildAndExpand(userId)
                .toUriString();

        ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
        return response.getBody();
    }
}
