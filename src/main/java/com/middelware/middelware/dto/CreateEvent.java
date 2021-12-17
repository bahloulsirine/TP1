package com.middelware.middelware.dto;

import com.middelware.middelware.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEvent {
    private Long id;
    private String title;
    private String description;
    private int numberPlace;
    private Date date;
    private Integer price;
    private String place;
    private Long coachId;
}
