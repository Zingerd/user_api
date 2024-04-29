package com.example.user_api.service;

import com.example.user_api.dto.DateRangeDTO;
import com.example.user_api.dto.UserRequestDTO;
import com.example.user_api.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    public UserRequestDTO createUser(UserRequestDTO userRq);

    public UserRequestDTO updateUserFields(Long id, UserRequestDTO userRq);

    public UserResponseDTO updateAllUserFields(Long id, UserRequestDTO userRq);

    public String deleteUser(Long id);

    public List<UserResponseDTO> findUsersByBirthDateRange(DateRangeDTO dateRange);
}
