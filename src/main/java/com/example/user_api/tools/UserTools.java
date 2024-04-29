package com.example.user_api.tools;

import com.example.user_api.dto.DateRangeDTO;
import com.example.user_api.dto.UserRequestDTO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UserTools {

    public static void verificationOfPersonMajority(UserRequestDTO userRq, int minimumAge) {
        if (ChronoUnit.YEARS.between(userRq.getBirthDate(), LocalDateTime.now()) < minimumAge) {
            throw new IllegalArgumentException(String.format("The person %s is a minor, he is %s ",
                    userRq.getFirstName(), ChronoUnit.YEARS.between(userRq.getBirthDate(), LocalDateTime.now())));
        }
    }

    public static void verificationCorrectRange(DateRangeDTO dateRange) {
        if (dateRange.getFrom().isAfter(dateRange.getTo())) {
            throw new IllegalArgumentException(String.format("{From: %s}  should be less than {To: %s}",
                    dateRange.getFrom(), dateRange.getTo()));
        }
    }

}
