package com.example.user_api.service;

import com.example.user_api.dto.DateRange;
import com.example.user_api.dto.UserRq;
import com.example.user_api.dto.UserRs;
import com.example.user_api.entity.User;
import com.example.user_api.repository.UserRepository;
import com.example.user_api.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    void testCreateUser() {
        UserRq userRq = buildUserRq();
        User user = buildUser();

        when(modelMapper.map(userRq, User.class)).thenReturn(user);
        userService.createUser(userRq);

        verify(userRepository).save(userCaptor.capture());
        User userRs = userCaptor.getValue();

        assertEquals(user.getId(), userRs.getId());
        assertEquals(user.getBirthDate(), userRs.getBirthDate());
        assertEquals(user.getEmail(), userRs.getEmail());
    }

    @Test
    void testUpdateUserFields() {
        UserRq userRq = buildUserRq();
        User user = buildUser();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.updateUserFields(user.getId(), userRq);

        verify(userRepository).save(userCaptor.capture());
        User userRs = userCaptor.getValue();

        assertEquals(user.getId(), userRs.getId());
        assertEquals(user.getBirthDate(), userRs.getBirthDate());
        assertEquals(user.getEmail(), userRs.getEmail());
    }

    @Test
    void testUpdateUserFields_UserNotFound() {
        Long userId = 1L;
        UserRq userRq = buildUserRq();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.updateUserFields(userId, userRq));
    }

    @Test
    void testUpdateAllUserFields() {
        UserRq userRq = buildUserRq();
        User user = buildUser();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.updateAllUserFields(user.getId(), userRq);

        verify(userRepository).save(userCaptor.capture());
        User userRs = userCaptor.getValue();

        assertEquals(user.getId(), userRs.getId());
        assertEquals(user.getBirthDate(), userRs.getBirthDate());
        assertEquals(user.getEmail(), userRs.getEmail());
    }

    @Test
    void testUpdateAllUserFields_UserNotFound() {
        Long userId = 1L;
        UserRq userRq = buildUserRq();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.updateAllUserFields(userId, userRq));
    }

    @Test
    void testDeleteUser() {
        User user = buildUser();
        when(userRepository.findById(buildUser().getId())).thenReturn(Optional.of(user));

        assertEquals("User with id: 1 deleted", userService.deleteUser(buildUser().getId()));
    }

    @Test
    void testDeleteUser_UserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(userId));
    }

    @Test
    void testFindUsersByBirthDateRange() {
        DateRange dateRange = new DateRange();
        dateRange.setFrom(LocalDate.of(1990, 1, 1));
        dateRange.setTo(LocalDate.of(2024, 1, 1));

        when(userRepository.findByBirthDateBetween(any(), any())).thenReturn(List.of(buildUser()));
        List<UserRs> result = userService.findUsersByBirthDateRange(dateRange);

        assertEquals(1, result.size());
    }

    private UserRq buildUserRq() {
        UserRq userRq = new UserRq();
        userRq.setFirstName("Mark");
        userRq.setLastName("Braun");
        userRq.setEmail("email@gmail.com");
        userRq.setBirthDate(LocalDate.of(1990, 1, 1));
        userRq.setAddress("UK");
        userRq.setPhoneNumber("33333333");
        return userRq;
    }
    private User buildUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Mark");
        user.setLastName("Braun");
        user.setEmail("email@gmail.com");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setAddress("UK");
        user.setPhoneNumber("33333333");
        return user;
    }
}
