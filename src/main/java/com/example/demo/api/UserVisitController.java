package com.example.demo.api;

import com.example.demo.api.exceptions.BadRequestBody;
import com.example.demo.dto.StatisticsDto;
import com.example.demo.dto.UserVisitDto;
import com.example.demo.entity.UserVisit;
import com.example.demo.service.UserVisitService;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserVisitController {

    private final UserVisitService userVisitService;

    public UserVisitController(UserVisitService userVisitService) {
        this.userVisitService = userVisitService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public UserVisit save(@RequestBody UserVisitDto userVisitDto) {
        if(StringUtils.isBlank(userVisitDto.ip()) || userVisitDto.date()== null ) {
            throw new BadRequestBody("Missing body parameter");
        }
        return userVisitService.save(UserVisitDto.toEntity(userVisitDto));
    }

    @GetMapping("/statistics")
    public List<StatisticsDto> getStatistics() {
        return userVisitService.getStatistics();
    }


    @ExceptionHandler(BadRequestBody.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionHandler(Exception e) {
        return e.getMessage();
    }
}
