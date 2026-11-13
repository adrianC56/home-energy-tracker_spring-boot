package com.macadribacks.user_service.service;

import com.macadribacks.user_service.dto.UserDTO;
import com.macadribacks.user_service.entity.User;
import com.macadribacks.user_service.exception.UserNotFoundException;
import com.macadribacks.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO input) {
        final User createdUser = User.builder()
                .name(input.name())
                .surname(input.surname())
                .email(input.email())
                .address(input.address())
                .alerting(input.alerting())
                .energyAlertingThreshold(input.energyAlertingThreshold())
                .build();

        final User saved = userRepository.save(createdUser);
        return toDto(saved);
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setName(userDTO.name());
        user.setSurname(userDTO.surname());
        user.setEmail(userDTO.email());
        user.setAddress(userDTO.address());
        user.setEnergyAlertingThreshold(user.getEnergyAlertingThreshold());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    private UserDTO toDto (User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .address(user.getAddress())
                .alerting(user.isAlerting())
                .energyAlertingThreshold(user.getEnergyAlertingThreshold())
                .build();
    }

}
