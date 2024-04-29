package com.example.user_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DateRangeDTO {
    @NotNull
    LocalDate from;
    @NotNull
    LocalDate to;
}
