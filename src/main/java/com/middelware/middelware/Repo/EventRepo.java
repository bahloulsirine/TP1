package com.middelware.middelware.Repo;

import com.middelware.middelware.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event,Long> {
    List<Event> getEventsByIsAccepted(Boolean test);
    List<Event> getEventsByPlace(String place);
    List<Event>getEventsByTitle(String title);
    List<Event>getEventsByCoachId(Long coachId);
    List<Event>getEventsByNumberPlaceEqualsAndIsAcceptedTrue(Integer x);
    List<Event>getEventsByNumberPlaceGreaterThanAndIsAcceptedTrue(Integer x);
    List<Event>getEventsByNumberPlaceGreaterThanEqualAndIsAcceptedTrue(Integer x);
    List<Event>getEventsByIsAcceptedTrueAndCoachIdAndAndNumberPlaceGreaterThanEqual(Long coachId,Integer x);
    List<Event>getEventsByIsAcceptedTrueAndCoachIdAndAndNumberPlaceGreaterThan(Long coachId,Integer x);
    List<Event>getEventsByCoachIdAndNumberPlaceEqualsAndIsAcceptedTrue(Long coachId,Integer x);
    List<Event>getEventsByCoachIdAndIsAccepted(Long coachId,Boolean test);

    List<Event> getEventsByIdIn(List<Long> ids);
    List<Event>getEventsByCoachIdAndIdIn(Long coachId,List<Long> ids);


}
