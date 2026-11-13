package com.macadribacks.device_service.service;

import com.macadribacks.device_service.dto.DeviceDTO;
import com.macadribacks.device_service.entity.Device;
import com.macadribacks.device_service.exception.DeviceNotFoundException;
import com.macadribacks.device_service.repository.DeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private DeviceRepository deviceRepository;

    public DeviceService (DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceDTO getDeviceById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found with id " + id));
        return mapToDTO(device);
    }

    public DeviceDTO createDevice (DeviceDTO input) {
        Device device = new Device();
        device.setName(input.name());
        device.setType(input.type());
        device.setLocation(input.location());
        device.setUserId(input.userId());

        Device savedDevice = deviceRepository.save(device);
        return mapToDTO(savedDevice);
    }
    public DeviceDTO updateDevice(Long id, DeviceDTO input) {
        Device existingDevice = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found with id " + id));
        existingDevice.setName(input.name());
        existingDevice.setType(input.type());
        existingDevice.setLocation(input.location());
        existingDevice.setUserId(input.userId());

        final Device updatedDevice = deviceRepository.save(existingDevice);
        return mapToDTO(updatedDevice);

    }

    public void deleteService(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new DeviceNotFoundException("Device not found with id " + id);
        }
        deviceRepository.deleteById(id);
    }


    private DeviceDTO mapToDTO(Device device){
        return DeviceDTO.builder()
                .id(device.getId())
                .name(device.getName())
                .type(device.getType())
                .location(device.getLocation())
                .userId(device.getUserId())
                .build();
    }



}
