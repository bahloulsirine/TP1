package com.middelware.middelware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeam {
    private Long id;
    private String name;
    private Boolean isPrivate;
    private Integer numberPlace;
}
