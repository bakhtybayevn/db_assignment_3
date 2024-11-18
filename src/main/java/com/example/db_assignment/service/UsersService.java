package com.example.db_assignment.service;

import com.example.db_assignment.entity.Users;
import com.example.db_assignment.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {
    private final UsersRepository userRepository;

    public UsersService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserByEmail(String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }

    @Transactional
    public int deleteUsersByNameContaining(String substring1, String substring2) {
        return userRepository.deleteUsersByNameContaining(substring1.toLowerCase(), substring2.toLowerCase());
    }
}
