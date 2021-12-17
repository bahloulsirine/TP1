package com.middelware.middelware.Repo;

import com.middelware.middelware.Models.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventRepo extends JpaRepository<UserEvent,Long> {
    List<UserEvent> getUserEventsByEventIdAndIsAcceptedTrue(Long eventId);
    List<UserEvent> getUserEventsByEventIdAndIsAcceptedFalse(Long eventId);
    List<UserEvent> getUserEventsByUserParticipatedIdAndIsAcceptedTrue(Long userId);
    List<UserEvent> getUserEventsByUserParticipatedIdAndIsAcceptedFalse(Long userId);
    UserEvent getUserEventByEventIdAndUserParticipatedId(Long eventId,Long userParticipatedId);

    //liste des ids des event ayant au - un particpant n'est pas encore validé
    @Query(value = "SELECT DISTINCT event_id FROM user_event WHERE is_accepted = false; ", nativeQuery = true)
    List<Long> getEventsNotAcceptedIds();
}
