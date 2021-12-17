package com.middelware.middelware.Repo;

import com.middelware.middelware.Models.Team;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team,Long> {
    Team getTeamByName(String teamName);
    List<Team>getTeamsByIsCreated(Boolean test);
    List<Team>getTeamsByIsPrivate(Boolean test);
    List<Team>getTeamsByCoachId(Long coachId);
    List<Team>getTeamsByNumberPlaceEqualsAndIsCreatedTrue(Integer x);
    List<Team>getTeamsByNumberPlaceGreaterThanAndIsCreatedTrue(Integer x);
    List<Team>getTeamsByNumberPlaceGreaterThanEqualAndIsCreatedTrue(Integer x);
    List<Team>getTeamsByCoachIdAndNumberPlaceEqualsAndIsCreatedTrue(Long coachId,Integer x);
    List<Team>getTeamsByCoachIdAndNumberPlaceGreaterThanAndIsCreatedTrue(Long coachId,Integer x);
    List<Team>getTeamsByCoachIdAndNumberPlaceGreaterThanEqualAndIsCreatedTrue(Long coachID,Integer x);
    List<Team>getTeamsByCoachIdAndIsCreated(Long coachId,Boolean test);
    List<Team>getTeamsByIdIn(List<Long> ids);
    List<Team>getTeamsByCoachIdAndIdIn(Long coachId,List<Long> ids);

}
