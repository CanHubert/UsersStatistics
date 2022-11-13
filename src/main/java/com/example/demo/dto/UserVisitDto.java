package com.example.demo.dto;

import com.example.demo.entity.UserVisit;

import java.time.LocalDate;

public record UserVisitDto(LocalDate date, String ip) {

    public static UserVisit toEntity(UserVisitDto dto) {
        return new UserVisit(dto.date, dto.ip);
    }
}
