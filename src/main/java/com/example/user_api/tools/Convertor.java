package com.example.user_api.tools;

import com.example.user_api.dto.UserRequestDTO;
import com.example.user_api.dto.UserResponseDTO;
import com.example.user_api.entity.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Convertor {

    public static UserRequestDTO convertUserToUserRequestDTO(User user, ModelMapper modelMapper) {
        return modelMapper.map(user, UserRequestDTO.class);
    }

    public static User convertUserRequestDTOToUser(UserRequestDTO user, ModelMapper modelMapper) {
        return modelMapper.map(user, User.class);
    }

    public static UserResponseDTO convertUserToUserResponseDTO(User user, ModelMapper modelMapper) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public static List<UserResponseDTO> convertListUserToListUserResponseDTO(List<User> task, ModelMapper modelMapper) {
        return task.stream()
                .map(task1 -> modelMapper.map(task1, UserResponseDTO.class))
                .collect(Collectors.toList());
    }
}
