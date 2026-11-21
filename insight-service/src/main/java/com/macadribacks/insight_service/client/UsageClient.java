package com.macadribacks.insight_service.client;

import com.macadribacks.insight_service.dto.UsageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UsageClient {

    private final RestTemplate restTemplate;

    private final String baseURL;

    public UsageClient(@Value("${usage.service.url}") String baseURL) {
        this.restTemplate = new RestTemplate();
        this.baseURL = baseURL;
    }

    public UsageDTO getXDaysUsageForUser(Long userId, int days) {
        String url = UriComponentsBuilder
                .fromUriString(baseURL)
                .path("/{userId}")
                .queryParam("days", days)
                .buildAndExpand(userId)
                .toUriString();

        ResponseEntity<UsageDTO> response = restTemplate.getForEntity(url, UsageDTO.class);
        return response.getBody();
    }
}
