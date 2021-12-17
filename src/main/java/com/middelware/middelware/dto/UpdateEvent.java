package com.middelware.middelware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEvent {
    private Long id;
    private String title;
    private String description;
    private int numberPlace;
    private Date date;
    private String place;
}