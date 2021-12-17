package com.middelware.middelware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeam {
    private Long id;
    private String name;
    private Boolean isPrivate;
    private Integer numberPlace;
    private Long coachId;
}
