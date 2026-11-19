package com.macadribacks.usage_service.client;

import com.macadribacks.usage_service.dto.DeviceDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class DeviceClient {

    private final RestTemplate restTemplate;

    private final String baseURL;

    public  DeviceClient(@Value("${device.service.url}") String baseURL) {
        this.restTemplate = new RestTemplate();
        this.baseURL = baseURL;
    }

    public DeviceDTO getDeviceById(Long deviceId) {
        String url = UriComponentsBuilder
                .fromUriString(baseURL)
                .path("/{deviceId}")
                .buildAndExpand(deviceId)
                .toUriString();

        ResponseEntity<DeviceDTO> response = restTemplate.getForEntity(url, DeviceDTO.class);
        return response.getBody();
    }
}
