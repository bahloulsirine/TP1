package com.middelware.middelware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipatedEvent {
    private Long id;
    private Long  eventId;
    private List<String> mails;
    private Long coachId;
}
