package com.example.demo.service;

import com.example.demo.dto.StatisticsDto;
import com.example.demo.entity.UserVisit;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserVisitServiceTest {


    @Autowired
    private UserVisitService service;

    @Before
    public void prepareData() {
        String ip = "1.2.3.4";
        saveEntity(2022,11,11, ip);
        saveEntity(2022,11,11, ip);
        saveEntity(2022,11,12, ip);
        saveEntity(2022,11,12, ip);
        saveEntity(2022,11,13, ip);
    }

    @Test
    public void test(){
        List<StatisticsDto> statistics = service.getStatistics();

        Assertions.assertFalse(statistics.isEmpty());
        Assertions.assertEquals(3, statistics.size());
    }


    private void saveEntity(int year, int month, int day, String ip) {
        service.save(new UserVisit(LocalDate.of(year,month,day), ip));
    }

}
