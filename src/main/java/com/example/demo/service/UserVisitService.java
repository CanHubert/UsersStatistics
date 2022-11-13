package com.example.demo.service;

import com.example.demo.dto.StatisticsDto;
import com.example.demo.entity.UserVisit;
import com.example.demo.repository.UserVisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserVisitService {

    private final UserVisitRepository userVisitRepository;

    public UserVisitService(UserVisitRepository userVisitRepository) {
        this.userVisitRepository = userVisitRepository;
    }

    public UserVisit save(UserVisit userVisit) {
        return userVisitRepository.save(userVisit);
    }

    public List<StatisticsDto> getStatistics() {
        return userVisitRepository.getStatistics();
    }
}
