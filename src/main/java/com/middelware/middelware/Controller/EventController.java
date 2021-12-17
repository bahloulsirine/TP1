package com.middelware.middelware.Controller;

import com.middelware.middelware.Models.Event;
import com.middelware.middelware.Services.EventService;
import com.middelware.middelware.dto.CreateEvent;

import com.middelware.middelware.dto.UpdateEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/Event")
@AllArgsConstructor
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventController {
    @Autowired
    private EventService eventService;

   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")//valid
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/getEvent/{id}")//valid
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEventByID(id);
    }

    @GetMapping("/getAcceptedEvents")//valid
    public List<Event> getAcceptedEvents() {
        return eventService.getAcceptedEvent();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getNotAcceptedEvents")//valid
    public List<Event> getNotAcceptedEvents() {
        return eventService.getNotAcceptedEvents();
    }

    @GetMapping("/getEventsPlace/{place}")//valid
    public List<Event> getEventByPlace(@PathVariable String place) {
        return eventService.getEventsByPlace(place);
    }

    @GetMapping("/getEventsTitle/{title}")//valid
    public List<Event> getEventByTitle(@PathVariable String title) {
        return eventService.getEventsByTitle(title);
    }

    @GetMapping("/getEventsCoach/{coachId}")//valid
    public List<Event> getEventByCoach(@PathVariable Long coachId) {
        return eventService.getEventsByCoach(coachId);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getCompletEvents")//valid
    public List<Event> getCompletEvents() {
        return eventService.getEventsComplet();
    }

    @GetMapping("/getNotCompletEvents")//valid
    public List<Event> getNotCompletEvents() { return eventService.getEventsDispo(); }

    @GetMapping("/getEventsMoreThan/{minDispo}")//valid
    public List<Event> getEventMoreThan(@PathVariable Integer minDispo) {
        return eventService.getEventsMoreThan(minDispo);}

    //@PreAuthorize("hasAnyRole('ADMIN','COACH')")
    @GetMapping("/getEventsCompletByCoach/{coachId}")//valid
    public List<Event> getEventsByCoachComplet(@PathVariable Long coachId) {
        return eventService.getEventsByCoachComplet(coachId);}

    @GetMapping("/getEventsDispoByCoach/{coachId}")//valid
    public List<Event> getEventsByCoachDispo(@PathVariable Long coachId) {
        return eventService.getEventsByCoachDispo(coachId);
    }
    @GetMapping("/getEventsMoreThanByCoach/{coachId}/{min}")//valid
    public List<Event> getEventsByCoachMoreThan(@PathVariable Long coachId,@PathVariable Integer min) {
        return eventService.getEventsByCoachIdMore(coachId,min);}

    @GetMapping("/getEventsAcceptedByCoach/{coachId}")//valid
    public List<Event> getEventsAcceptedByCoach(@PathVariable Long coachId) {
        return eventService.getEventsByCoachAccepted(coachId);
    }
    //@PreAuthorize("hasAnyRole('ADMIN','COACH')")
    @GetMapping("/getEventsNotAcceptedByCoach/{coachId}")//valid
    public List<Event> getEventsNotAcceptedByCoach(@PathVariable Long coachId) {
        return eventService.getEventsByCoachNotAccepted(coachId);
    }
   // @PreAuthorize("hasRole('COACH')")
    @GetMapping("/getEventsNotAcceptedParticipated/{coachId}")//valid
    public List<Event> getEventsNotAcceptedParticipated(@PathVariable Long coachId) {
        return eventService.getEventsNotAcceptedParticipated(coachId);
    }
   // @PreAuthorize("hasAnyRole('ADMIN','COACH')")
    @PostMapping("/new")//valid
    public Event createEvent(@RequestBody CreateEvent createEvent) {
        return eventService.createEvent(createEvent);}

    //@PreAuthorize("hasRole('COACH')")
    @PutMapping("")//valid
    public Event updateEventCoach(@RequestBody UpdateEvent updateEvent) {
        return eventService.updateEventCoach(updateEvent);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin")//valid
    public Event updateEventAdmin(@RequestBody Event event) {
        return eventService.updateEvents(event);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/validateEvent/{eventId}")//valid
    public Event validateEvent(@PathVariable Long eventId) {
        return eventService.validateEvent(eventId);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/deleteEvent/{eventId}")//valid
    public Event deleteEvent(@PathVariable Long eventId) {
        return eventService.deleteEvent(eventId);
    }

}
