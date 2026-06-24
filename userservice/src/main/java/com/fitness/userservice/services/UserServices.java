package com.fitness.userservice.services;

import com.fitness.userservice.UserRepository;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServices {

    private final  UserRepository repository;
    public UserResponse register(RegisterRequest request) {

        if(repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User saveUser = repository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(saveUser.getId());
        userResponse.setEmail(saveUser.getEmail());
        userResponse.setFirstName(saveUser.getFirstName());
        userResponse.setLastName(saveUser.getLastName());
        userResponse.setCreatedAt(saveUser.getCreatedAt());
        userResponse.setUpdatedAt(saveUser.getUpdatedAt());
        return userResponse;
    }

    public UserResponse getUserById(String userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}
