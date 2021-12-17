package com.middelware.middelware.Services;

import com.middelware.middelware.Models.Event;
import com.middelware.middelware.Models.User;
import com.middelware.middelware.Models.UserEvent;
import com.middelware.middelware.Repo.EventRepo;
import com.middelware.middelware.Repo.UserEventRepo;
import com.middelware.middelware.dto.CreateParticipatedEvent;
import com.middelware.middelware.dto.CreateUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Transactional
public class UserEventService {
    @Autowired
    private UserEventRepo userEventRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepo eventRepo;
    public UserEvent participate(CreateUserEvent createUserEvent){
        Event event=eventService.getEventByID(createUserEvent.getEventId());
        if((event.getIsAccepted()==true)&&(event.getNumberPlace()>0)){

            UserEvent userEvent=userEventRepo.getUserEventByEventIdAndUserParticipatedId(event.getId(), createUserEvent.getUserId());
            if(userEvent==null){
                User user=userService.getUserById(createUserEvent.getUserId());
                UserEvent newUserEvent=new UserEvent(null,false,event,user);
                return userEventRepo.save(newUserEvent);
            }
        }
    return null;
    }
    //système crée les userEvent selon la liste des mails
    public List<UserEvent> createParticipated(CreateParticipatedEvent createParticipatedEvent){
        Event event=eventService.getEventByID(createParticipatedEvent.getEventId());
        User coach=userService.getUserById(createParticipatedEvent.getCoachId());
        List<Event>events=eventService.getEventsByCoach(coach.getId());
        if (events.contains(event)){
        List<String> mails=createParticipatedEvent.getMails();
        if((event.getNumberPlace()>= mails.size())&&(event.getIsAccepted()==true)){
            List<UserEvent> results=new ArrayList<>();
            for (String mail:mails){
                User user=userService.getUserByEmail(mail);
                UserEvent userEvent=userEventRepo.getUserEventByEventIdAndUserParticipatedId(event.getId(),user.getId());
                if(userEvent==null){
                    event.setNumberPlace(event.getNumberPlace()-1);
                    eventRepo.save(event);
                    UserEvent newUserEvent=new UserEvent(null,true,event,user);
                    results.add(newUserEvent);
                    userEventRepo.save(newUserEvent);
                }
                else if (userEvent.getIsAccepted()==false){
                    event.setNumberPlace(event.getNumberPlace()-1);
                    eventRepo.save(event);
                    userEvent.setIsAccepted(true);
                    results.add(userEvent);
                    userEventRepo.save(userEvent);
                }
            }
            return results;
        }}
        return null;
    }
    public UserEvent getUserEventById(Long id){return userEventRepo.findById(id).get();}
    public List<User> getParticipatedByEvent(Long eventId,Long currentUser_id){
        //get idUser connecté --> 1L puis has_Role
        User user = userService.getUserById(currentUser_id);
        if(user.hasRole("ADMIN")) {
            return getParticipated(eventId);
        }
        else if (user.hasRole("COACH")){
            List<Event>events=eventService.getEventsByCoach(user.getId());
            if(events.contains(eventService.getEventByID(eventId))){
                return getParticipated(eventId);
            }}
        List<Event> myEvents=getEventsAcceptedByParticipantId(currentUser_id);
        if (myEvents.contains(eventService.getEventByID(eventId))){
            return getParticipated(eventId);
        }

        return null;
    }
    public List<User> getParticipated(Long eventId){
            List<UserEvent> userEvents=userEventRepo.getUserEventsByEventIdAndIsAcceptedTrue(eventId);
            List<User> users=new ArrayList<>();
            for (UserEvent userEvent :userEvents){
                users.add(userEvent.getUserParticipated());
            }
            return users;
    }

    public List<User> getNotParticipatedByEvent(Long eventId,Long currentUser){
        User user = userService.getUserById(currentUser);
        List<Event>events=eventService.getEventsByCoach(user.getId());
        if(events.contains(eventService.getEventByID(eventId))){
            List<UserEvent> userEvents=userEventRepo.getUserEventsByEventIdAndIsAcceptedFalse(eventId);
            List<User> users=new ArrayList<>();
            for (UserEvent userEvent :userEvents){
                users.add(userEvent.getUserParticipated());}
            return users;
        }
        return null;}


    public Integer numberParticipated(Long eventId,Long currentUser){
        List<User> users=getParticipatedByEvent(eventId,currentUser);
        return users.size();}
    public Integer numberNotParticipated(Long eventId,Long currentUser){
        List<User> users=getNotParticipatedByEvent(eventId,currentUser);
        return users.size();}

    //histroque du participant
    //user Participated connecté
    public List<Event> getEventsAcceptedByParticipantId(Long currentUser){
        User user = userService.getUserById(currentUser);
        List<UserEvent> userEvents=userEventRepo.getUserEventsByUserParticipatedIdAndIsAcceptedTrue(user.getId());
        List<Event> events=new ArrayList<>();
        for (UserEvent userEvent:userEvents){
            events.add(userEvent.getEvent());}
        return  events;}
    public Integer numberEventsAcceptedByParticipant(Long currentUser){
        List<Event> events=getEventsAcceptedByParticipantId(currentUser);
        return events.size();}
    public List<Event> getEventsNotAcceptedByParticipantId(Long currentUser){
        User user = userService.getUserById(currentUser);
        List<UserEvent> userEvents=userEventRepo.getUserEventsByUserParticipatedIdAndIsAcceptedFalse(user.getId());
        List<Event> events=new ArrayList<>();
        for (UserEvent userEvent:userEvents){
            events.add(userEvent.getEvent());}
        return  events;}
    public Integer numberEventsNotAcceptedByParticipant(Long currentUser){
        List<Event> events=getEventsNotAcceptedByParticipantId(currentUser);
        return events.size();}
    public List<UserEvent> allUserEvents(){return userEventRepo.findAll();}
    //get coach connecté
    public UserEvent validateParticipant(Long userEventId,Long idCoach){
        UserEvent userEvent=getUserEventById(userEventId);
        Event event=userEvent.getEvent();
        User user = userService.getUserById(idCoach);
        List<Event>events=eventService.getEventsByCoach(user.getId());
        if (events.contains(event)){
            if(event.getNumberPlace()>0){
                event.setNumberPlace(event.getNumberPlace()-1);
                eventRepo.save(event);
                userEvent.setIsAccepted(true);
                return userEventRepo.save(userEvent);}}
        return null;}
    public UserEvent deleteUserEvent(Long userEventId,Long coachId){
        UserEvent userEvent=getUserEventById(userEventId);
        Event event=userEvent.getEvent();
        User user = userService.getUserById(coachId);
        List<Event>events=eventService.getEventsByCoach(user.getId());
        if (events.contains(event)){
        userEvent.setIsAccepted(false);
        return userEventRepo.save(userEvent);}
        return null;
    }

    List<Long>getEventIdsNotAccepted(){
        return userEventRepo.getEventsNotAcceptedIds();}


}
