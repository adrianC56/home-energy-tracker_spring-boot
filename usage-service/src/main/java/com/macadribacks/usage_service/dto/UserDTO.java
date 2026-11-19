package com.macadribacks.usage_service.dto;

import lombok.Builder;

@Builder
public record UserDTO(
        Long id,
        String name,
        String surname,
        String email,
        String address,
        boolean alerting,
        double energyAlertingThreshold
) {}
