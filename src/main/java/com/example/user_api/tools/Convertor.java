package com.example.user_api.tools;

import com.example.user_api.dto.UserRq;
import com.example.user_api.dto.UserRs;
import com.example.user_api.entity.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Convertor {

    public static UserRq convertUserToUserRq(User user, ModelMapper modelMapper) {
        return modelMapper.map(user, UserRq.class);
    }

    public static User convertUserRqToUser(UserRq user, ModelMapper modelMapper) {
        return modelMapper.map(user, User.class);
    }

    public static UserRs convertUserToUserRs(User user, ModelMapper modelMapper) {
        return modelMapper.map(user, UserRs.class);
    }

    public static List<UserRs> convertListUserToListUserRs(List<User> task, ModelMapper modelMapper) {
        return task.stream()
                .map(task1 -> modelMapper.map(task1, UserRs.class))
                .collect(Collectors.toList());
    }
}
