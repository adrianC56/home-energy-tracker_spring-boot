package com.macadribacks.user_service.dto;

import lombok.Builder;

@Builder
public record UserDTO(
        Long id,
        String name,
        String surname,
        String email,
        String address,
        Boolean alerting,
        Double energyAlertingThreshold
) {}
