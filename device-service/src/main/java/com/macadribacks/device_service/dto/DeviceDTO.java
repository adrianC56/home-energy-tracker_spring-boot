package com.macadribacks.device_service.dto;

import com.macadribacks.device_service.model.DeviceType;
import lombok.Builder;

@Builder
public record DeviceDTO(
        Long id,
        String name,
        DeviceType type,
        String location,
        Long userId
) {
}
