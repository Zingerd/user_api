package com.example.user_api.controller;

import com.example.user_api.dto.DateRangeDTO;
import com.example.user_api.dto.UserRequestDTO;
import com.example.user_api.dto.UserResponseDTO;
import com.example.user_api.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.user_api.tools.UserTools.verificationCorrectRange;
import static com.example.user_api.tools.UserTools.verificationOfPersonMajority;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Value("${minimum.age}")
    private int minimumAge;

    private final UserServiceImpl userService;

    UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Method create user.
     *
     * @param userRq request with mandatory user fields
     * @return return user
     */
    @Operation(
            summary = "Create user")
    @PostMapping
    public ResponseEntity<UserRequestDTO> createUser(@Valid @RequestBody UserRequestDTO userRq) {
        verificationOfPersonMajority(userRq, minimumAge);
        return ResponseEntity.ok(userService.createUser(userRq));
    }

    /**
     * Method partial update user.
     *
     * @param userRq request with mandatory user fields
     * @param id     user
     * @return ResponseEntity<UserRq> response with partial update user
     */
    @Operation(
            summary = "Method partial update user")
    @PatchMapping("/{id}")
    public ResponseEntity<UserRequestDTO> updateUserFields(@PathVariable Long id, @RequestBody UserRequestDTO userRq) {
        verificationOfPersonMajority(userRq, minimumAge);
        return ResponseEntity.ok(userService.updateUserFields(id, userRq));
    }

    /**
     * Method update user
     *
     * @param userRq request with mandatory user fields
     * @param id     user id
     * @return ResponseEntity<UserRq> response with update user
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateAllUserFields(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRq) {
        verificationOfPersonMajority(userRq, minimumAge);
        return ResponseEntity.ok(userService.updateAllUserFields(id, userRq));
    }

    /**
     * Method delete user
     *
     * @param id user id
     * @return ResponseEntity<String> response about deleting the user
     */
    @Operation(
            summary = "Method delete user")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    /**
     * Method search user by birthdate range
     *
     * @param dateRange mandatory object with date range
     * @return ResponseEntity<String> response about find user by birthdate range
     */
    @Operation(
            summary = "Method search user by birthdate range")
    @PostMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchUsersByBirthDateRange(@RequestBody DateRangeDTO dateRange) {
        verificationCorrectRange(dateRange);
        List<UserResponseDTO> users = userService.findUsersByBirthDateRange(dateRange);
        return ResponseEntity.ok(users);
    }
}
