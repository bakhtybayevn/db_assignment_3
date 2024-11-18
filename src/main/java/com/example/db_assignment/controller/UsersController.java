package com.example.db_assignment.controller;

import com.example.db_assignment.entity.Users;
import com.example.db_assignment.service.UsersService;
import com.example.db_assignment.entity.Country;
import com.example.db_assignment.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;
    private final CountryService countryService;

    public UsersController(UsersService usersService, CountryService countryService) {
        this.usersService = usersService;
        this.countryService = countryService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{email}")
    public Users getUserByEmail(@PathVariable String email) {
        return usersService.getUserByEmail(email);
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Map<String, Object> payload) {
        String cname = (String) payload.get("cname");
        Country country = countryService.getCountryByCname(cname);

        Users user = new Users();
        user.setEmail((String) payload.get("email"));
        user.setName((String) payload.get("name"));
        user.setSurname((String) payload.get("surname"));
        user.setSalary((Integer) payload.get("salary"));
        user.setPhone((String) payload.get("phone"));
        user.setCountry(country);

        Users savedUser = usersService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PatchMapping("/{email}")
    public ResponseEntity<Users> patchUser(@PathVariable String email, @RequestBody Map<String, Object> updates) {
        Users existingUser = usersService.getUserByEmail(email);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (updates.containsKey("email")) {
            String newEmail = (String) updates.get("email");

            if (usersService.getUserByEmail(newEmail) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(null);
            }

            existingUser.setEmail(newEmail);
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingUser.setName((String) value);
                    break;
                case "surname":
                    existingUser.setSurname((String) value);
                    break;
                case "salary":
                    existingUser.setSalary((Integer) value);
                    break;
                case "phone":
                    existingUser.setPhone((String) value);
                    break;
                case "cname":
                    String cname = (String) value;
                    Country country = countryService.getCountryByCname(cname);
                    existingUser.setCountry(country);
                    break;
                case "email":
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        // Save the updated user
        Users updatedUser = usersService.saveUser(existingUser);
        return ResponseEntity.ok(updatedUser);
    }



    @DeleteMapping("/{email}")
    public void deleteUser(@PathVariable String email) {
        usersService.deleteUser(email);
    }
}
