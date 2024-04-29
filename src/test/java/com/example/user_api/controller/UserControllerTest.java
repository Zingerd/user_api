package com.example.user_api.controller;
import com.example.user_api.dto.DateRange;
import com.example.user_api.dto.UserRq;
import com.example.user_api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;


    @Test
    void testCreateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\",\"email\":\"email@gmail.com\", \"birthDate\": \"1990-01-01\", \"address\": \"123 Main St\", \"phoneNumber\": \"123456789\" }"))
                .andExpect(status().isOk());

        verify(userService).createUser(any(UserRq.class));
    }

    @Test
    void testUpdateUserFields() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\",\"email\":\"email@gmail.com\", \"birthDate\": \"1990-01-01\", \"address\": \"123 Main St\", \"phoneNumber\": \"123456789\" }"))
                .andExpect(status().isOk());

        verify(userService).updateUserFields(eq(1L), any(UserRq.class));
    }

    @Test
    void testUpdateAllUserFields() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\",\"email\":\"email@gmail.com\", \"birthDate\": \"1990-01-01\", \"address\": \"123 Main St\", \"phoneNumber\": \"123456789\" }"))
                .andExpect(status().isOk());

        verify(userService).updateAllUserFields(eq(1L), any(UserRq.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/1"))
                .andExpect(status().isOk());

        verify(userService).deleteUser(1L);
    }

    @Test
    void testSearchUsersByBirthDateRange() throws Exception {
        when(userService.findUsersByBirthDateRange(any(DateRange.class))).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"from\": \"1990-01-01\", \"to\": \"2024-01-01\" }"))
                .andExpect(status().isOk());

        verify(userService).findUsersByBirthDateRange(any(DateRange.class));
    }
}

