package com.example.user_api.tools;

import com.example.user_api.dto.DateRange;
import com.example.user_api.dto.UserRq;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UserTools {

    public static void verificationOfPersonMajority(UserRq userRq, int minimumAge) {
        if (ChronoUnit.YEARS.between(userRq.getBirthDate(), LocalDateTime.now()) < minimumAge) {
            throw new IllegalArgumentException(String.format("The person %s is a minor, he is %s ",
                    userRq.getFirstName(), ChronoUnit.YEARS.between(userRq.getBirthDate(), LocalDateTime.now())));
        }
    }

    public static void verificationCorrectRange(DateRange dateRange) {
        if (dateRange.getFrom().isAfter(dateRange.getTo())) {
            throw new IllegalArgumentException(String.format("{From: %s}  should be less than {To: %s}",
                    dateRange.getFrom(), dateRange.getTo()));
        }
    }

}
