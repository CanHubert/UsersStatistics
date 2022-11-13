package com.example.demo.dto;

import java.time.LocalDate;

public class StatisticsDto {

    private LocalDate date;
    private long count;

    public StatisticsDto(LocalDate localDate, long count) {
        this.date = localDate;
        this.count = count;
    }

    public StatisticsDto() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
