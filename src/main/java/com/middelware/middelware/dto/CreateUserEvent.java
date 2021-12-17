package com.middelware.middelware.dto;

import com.middelware.middelware.Models.Event;
import com.middelware.middelware.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserEvent {
    private Long id;
    private Long  eventId;
    private Long userId;

}
