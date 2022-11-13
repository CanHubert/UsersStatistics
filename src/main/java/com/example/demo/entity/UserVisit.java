package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "UserVisit")
public class UserVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "localDate")
    private LocalDate localDate;

    @Column
    private String ip;


    public UserVisit() {
    }

    public UserVisit(LocalDate date, String ip) {
        this.localDate = date;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getIp() {
        return ip;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
