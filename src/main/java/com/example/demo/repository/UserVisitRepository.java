package com.example.demo.repository;

import com.example.demo.dto.StatisticsDto;
import com.example.demo.entity.UserVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserVisitRepository extends JpaRepository<UserVisit, Long> {


    @Query("SELECT new com.example.demo.dto.StatisticsDto(localDate , COUNT(localDate)) as count FROM UserVisit GROUP BY localDate ORDER BY localDate")
    List<StatisticsDto> getStatistics();
}
