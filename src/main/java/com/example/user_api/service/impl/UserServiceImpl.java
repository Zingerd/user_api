package com.example.user_api.service.impl;

import com.example.user_api.dto.DateRange;
import com.example.user_api.dto.UserRq;
import com.example.user_api.dto.UserRs;
import com.example.user_api.entity.User;
import com.example.user_api.repository.UserRepository;
import com.example.user_api.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.user_api.tools.Convertor.convertListUserToListUserRs;
import static com.example.user_api.tools.Convertor.convertUserRqToUser;
import static com.example.user_api.tools.Convertor.convertUserToUserRq;
import static com.example.user_api.tools.Convertor.convertUserToUserRs;


@Service
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRq createUser(UserRq userRq) {
        log.info("Create user");
        userRepository.save(convertUserRqToUser(userRq, modelMapper));
        return userRq;
    }

    @Override
    public UserRq updateUserFields(Long id, UserRq userRq) {
        log.info("update user field");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        updateUser(userRq, user);
        userRepository.save(user);
        return convertUserToUserRq(user, modelMapper);
    }

    @Override
    public UserRs updateAllUserFields(Long id, UserRq userRq) {
        log.info("update all user field");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        updateFildsExitsUser(user, userRq);
        userRepository.save(user);
        return convertUserToUserRs(user, modelMapper);
    }

    @Override
    public String deleteUser(Long id) {
        log.info("delete user");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        return String.format("User with id: %s deleted", id);
    }

    @Override
    public List<UserRs> findUsersByBirthDateRange(DateRange dateRange) {
        log.info("find user by birthday range");
        List<User> userList = userRepository.findByBirthDateBetween(dateRange.getFrom(), dateRange.getTo());

        return convertListUserToListUserRs(userList, modelMapper);
    }

    private void updateUser(UserRq userRq, User user) {
        if (userRq.getFirstName() != null) {
            user.setFirstName(user.getFirstName());
        }
        if (userRq.getLastName() != null) {
            user.setLastName(userRq.getLastName());
        }
        if (userRq.getBirthDate() != null) {
            user.setBirthDate(userRq.getBirthDate());
        }
        if (userRq.getAddress() != null) {
            user.setAddress(userRq.getAddress());
        }
        if (userRq.getPhoneNumber() != null) {
            user.setPhoneNumber(userRq.getPhoneNumber());
        }
    }

    private User updateFildsExitsUser(User user, UserRq userRq) {
        user.setAddress(userRq.getAddress());
        user.setLastName(userRq.getLastName());
        user.setBirthDate(userRq.getBirthDate());
        user.setFirstName(userRq.getFirstName());
        user.setEmail(userRq.getEmail());
        user.setPhoneNumber(userRq.getPhoneNumber());
        return user;
    }

}
