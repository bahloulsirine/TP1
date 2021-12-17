package com.middelware.middelware.Controller;

import com.middelware.middelware.Models.Event;
import com.middelware.middelware.Models.User;
import com.middelware.middelware.Models.UserEvent;
import com.middelware.middelware.Services.UserEventService;
import com.middelware.middelware.dto.CreateParticipatedEvent;
import com.middelware.middelware.dto.CreateUserEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/UserEvent")
@AllArgsConstructor
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserEventController {
    @Autowired
    private UserEventService userEventService;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public List<UserEvent>getAllUserEvents(){return userEventService.allUserEvents();}
    @GetMapping("/{id}")//valid
    public UserEvent getUserEventById(@PathVariable Long id) {
        return userEventService.getUserEventById(id);
    }

    @GetMapping("/participatedByEvent/{event_id}/{current_user}")
    public List<User> getParticipatedByEvent(@PathVariable Long event_id,@PathVariable Long current_user){
        return userEventService.getParticipatedByEvent(event_id,current_user);
    }
    @GetMapping("/numberParticipatedByEvent/{event_id}/{current_user}")
    public Integer getNumberParticipatedByEvent(@PathVariable Long event_id,@PathVariable Long current_user){
        return userEventService.numberParticipated(event_id,current_user);
    }

    //@PreAuthorize("hasRole('COACH')")
    @GetMapping("/notParticipatedByEvent/{event_id}/{current_user}")
    public List<User> getNotParticipatedByEvent(@PathVariable Long event_id,@PathVariable Long current_user){
        return userEventService.getNotParticipatedByEvent(event_id,current_user);
    }
    //@PreAuthorize("hasRole('COACH')")
    @GetMapping("/numberNotParticipatedByEvent/{event_id}/{current_user}")
    public Integer getNumberNotParticipatedByEvent(@PathVariable Long event_id,@PathVariable Long current_user){
        return userEventService.numberNotParticipated(event_id,current_user);
    }

    @GetMapping("/eventsAcceptedByClient/{current_user}")
    public List<Event> getEventsAcceptedByClient(@PathVariable Long current_user){
        return userEventService.getEventsAcceptedByParticipantId(current_user);
    }
    @GetMapping("/numberEventsAcceptedByClient/{coachId}")
    public Integer getNumberEventsAcceptedByClient(@PathVariable Long coachId){
        return userEventService.numberEventsAcceptedByParticipant(coachId);
    }
    @GetMapping("/eventsNotAcceptedByClient/{current_user}")
    public List<Event> getEventsNotAcceptedByClient(@PathVariable Long current_user){
        return userEventService.getEventsNotAcceptedByParticipantId(current_user);
    }
    @GetMapping("/numberEventsNotAcceptedByClient/{current_user}")
    public Integer getNumberEventsNotAcceptedByClient(@PathVariable Long current_user){
        return userEventService.numberEventsNotAcceptedByParticipant(current_user);
    }

    @PostMapping("")
    public UserEvent participate(@RequestBody CreateUserEvent createUserEvent)
        {return userEventService.participate(createUserEvent);}

    //@PreAuthorize("hasRole('COACH')")
    @PostMapping("/listMail")
    public List<UserEvent> createUserEvents(@RequestBody CreateParticipatedEvent createParticipatedEvent){
        return userEventService.createParticipated(createParticipatedEvent);
    }

    //@PreAuthorize("hasRole('COACH')")
    @PutMapping("/validate/{userEventId}/{current_user}")
    public UserEvent validateUserEvent(@PathVariable Long userEventId,@PathVariable Long current_user){return userEventService.validateParticipant(userEventId,current_user);}

    //@PreAuthorize("hasRole('COACH')")
    @PutMapping("/delete/{userEventId}/{current_user}")
    public UserEvent deleteUserEvent(@PathVariable Long userEventId,@PathVariable Long current_user){return userEventService.deleteUserEvent(userEventId,current_user);}




}
