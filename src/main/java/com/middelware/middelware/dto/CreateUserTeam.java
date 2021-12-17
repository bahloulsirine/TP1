package com.middelware.middelware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserTeam {
    private Long id;
    private Long  teamId;
    private Long userId;
}
