package com.example.demo.api;

import com.example.demo.dto.StatisticsDto;
import com.example.demo.dto.UserVisitDto;
import com.example.demo.service.UserVisitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserVisitControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int serverPort;

    @MockBean
    private UserVisitService service;


    public static Stream<Arguments> saveParameters() {
        return Stream.of(
                Arguments.of(new UserVisitDto(LocalDate.now(), "123.123.123.123"), HttpStatus.CREATED),
                Arguments.of(new UserVisitDto(null, "123.123.123.123"), HttpStatus.BAD_REQUEST),
                Arguments.of(new UserVisitDto(LocalDate.now(), ""), HttpStatus.BAD_REQUEST),
                Arguments.of(new UserVisitDto(null, null), HttpStatus.BAD_REQUEST)
        );
    }

    @ParameterizedTest
    @MethodSource("saveParameters")
    public void saveUserVisitTest(UserVisitDto dto, HttpStatus status) throws URISyntaxException {
        URI uri = createUri("/save");
        HttpEntity<UserVisitDto> httpEntity = new HttpEntity<>(dto);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, httpEntity, String.class);

        Assertions.assertEquals(status.value(), response.getStatusCode().value());
    }

    @Test
    public void getStatisticsTest() throws URISyntaxException, JsonProcessingException {
        URI uri = createUri("/statistics");


        Mockito.when(service.getStatistics()).thenReturn(getStatistics());
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        String body = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<StatisticsDto> statisticsDtos = mapper.readValue(body, new TypeReference<>() {
        });


        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        Assertions.assertEquals(getStatistics().size(), statisticsDtos.size());
    }


    private List<StatisticsDto> getStatistics() {
        StatisticsDto statisticsDto = new StatisticsDto(LocalDate.of(2022, 11,11), 3);
        StatisticsDto statisticsDto1 = new StatisticsDto(LocalDate.of(2022, 11,12), 2);
        StatisticsDto statisticsDto2 = new StatisticsDto(LocalDate.of(2022, 11,13), 4);
        StatisticsDto statisticsDto3 = new StatisticsDto(LocalDate.of(2022, 11,14), 1);
        return List.of(statisticsDto, statisticsDto1, statisticsDto2, statisticsDto3);
    }


    private URI createUri(String endpoint) throws URISyntaxException {
        return new URI("http://localhost:" + serverPort + endpoint);
    }
}
