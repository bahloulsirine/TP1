package com.middelware.middelware.Services;

import com.middelware.middelware.Models.Event;
import com.middelware.middelware.Models.User;
import com.middelware.middelware.Repo.EventRepo;
import com.middelware.middelware.dto.CreateEvent;
import com.middelware.middelware.dto.UpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Component
@Transactional
public class EventService {
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private UserEventService userEventService;

    public Event createEvent(CreateEvent createEvent){
        User coach=userService.getUserById(createEvent.getCoachId());
        Event event=new Event(null,createEvent.getTitle(),createEvent.getDescription(),createEvent.getNumberPlace(),createEvent.getDate(),createEvent.getPrice(),createEvent.getPlace(),false,coach);
        return eventRepo.save(event);}

    public Event updateEventCoach(UpdateEvent updateEvent){
            Event event=getEventByID(updateEvent.getId());
                event.setTitle(updateEvent.getTitle());
                event.setDescription(updateEvent.getDescription());
                Date date=new Date();
                if (updateEvent.getDate().after(date)){
                    event.setDate(updateEvent.getDate());
                }
                event.setPlace(updateEvent.getPlace());
                //test
                event.setNumberPlace(updateEvent.getNumberPlace());
                return eventRepo.save(event);
            }
    public Event updateEvents(Event event){return eventRepo.save(event);}

    public List<Event>getAllEvents(){return eventRepo.findAll();}
    public Event getEventByID(Long eventId){return eventRepo.findById(eventId).get();}
    public List<Event>getAcceptedEvent(){return eventRepo.getEventsByIsAccepted(true);}
    public List<Event>getNotAcceptedEvents(){return eventRepo.getEventsByIsAccepted(false);}
    public Event validateEvent(Long eventId){
        Event event=getEventByID(eventId);
        event.setIsAccepted(true);
        return eventRepo.save(event);}
    public List<Event>getEventsByPlace(String place){return eventRepo.getEventsByPlace(place);}
    // zeyda l delete howa eno l event isAccepted=false
    public  Event deleteEvent(Long eventId){
        Event event=getEventByID(eventId);
        event.setIsAccepted(false);
        return eventRepo.save(event);}
    public List<Event> getEventsByTitle(String title){return eventRepo.getEventsByTitle(title);}
    public List<Event> getEventsByCoach(Long coachId){return eventRepo.getEventsByCoachId(coachId);}
    public List<Event>getEventsComplet(){return eventRepo.getEventsByNumberPlaceEqualsAndIsAcceptedTrue(0);}
    public List<Event>getEventsDispo(){return eventRepo.getEventsByNumberPlaceGreaterThanAndIsAcceptedTrue(0);}
    public List<Event>getEventsMoreThan(Integer x){return eventRepo.getEventsByNumberPlaceGreaterThanEqualAndIsAcceptedTrue(x);}
    public List<Event>getEventsByCoachComplet(Long coachId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.hasRole("COACH")){
            return eventRepo.getEventsByCoachIdAndNumberPlaceEqualsAndIsAcceptedTrue(user.getId(),0);
        }
        return eventRepo.getEventsByCoachIdAndNumberPlaceEqualsAndIsAcceptedTrue(coachId,0);
    }
    public List<Event>getEventsByCoachDispo(Long coachId){return eventRepo.getEventsByIsAcceptedTrueAndCoachIdAndAndNumberPlaceGreaterThan(coachId,0);}
    public List<Event>getEventsByCoachIdMore(Long coachId,Integer x){return eventRepo.getEventsByIsAcceptedTrueAndCoachIdAndAndNumberPlaceGreaterThanEqual(coachId,x);}
    public List<Event> getEventsByCoachAccepted(Long coachId){return eventRepo.getEventsByCoachIdAndIsAccepted(coachId,true);}
    public List<Event> getEventsByCoachNotAccepted(Long coachId){
        return eventRepo.getEventsByCoachIdAndIsAccepted(coachId,false);}



    //liste des  event ayant au - un particpant n'est pas encore validé
    public List<Event>getEventsNotAcceptedParticipated(Long coachId){

        return eventRepo.getEventsByCoachIdAndIdIn(coachId,userEventService.getEventIdsNotAccepted());

    }

}
