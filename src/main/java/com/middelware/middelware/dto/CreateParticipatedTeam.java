package com.middelware.middelware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipatedTeam {

    private Long id;
    private Long  teamId;
    private List<String> mails;
    private Long userId;
}
