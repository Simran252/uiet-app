package com.uietkuk.app.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data

public class Announcement {


    private Long id;

    private String name;

    private String url;

    private LocalDate date;
}
