package com.example.user_api.service;

import com.example.user_api.dto.DateRange;
import com.example.user_api.dto.UserRq;
import com.example.user_api.dto.UserRs;

import java.util.List;

public interface UserService {

    public UserRq createUser(UserRq userRq);

    public UserRq updateUserFields(Long id, UserRq userRq);

    public UserRs updateAllUserFields(Long id, UserRq userRq);

    public String deleteUser(Long id);

    public List<UserRs> findUsersByBirthDateRange(DateRange dateRange);
}
