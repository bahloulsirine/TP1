package com.middelware.middelware.Services;


import com.middelware.middelware.Models.Team;
import com.middelware.middelware.Models.User;
import com.middelware.middelware.Repo.TeamRepo;
import com.middelware.middelware.dto.CreateTeam;
import com.middelware.middelware.dto.UpdateTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Component
@Transactional
public class TeamService {
    @Autowired
    private  TeamRepo teamRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private UserTeamService userTeamService;

    public Team createTeam(CreateTeam createTeam){
        User coach=userService.getUserById(createTeam.getCoachId());
        Team team =new Team(null,createTeam.getName(),false,createTeam.getIsPrivate(),createTeam.getNumberPlace(),coach);
        return teamRepo.save(team);}
    public Team updateTeamCoach(UpdateTeam updateTeam){
        Team team=getTeamById(updateTeam.getId());

                team.setIsPrivate(updateTeam.getIsPrivate());
                team.setName(updateTeam.getName());
                team.setNumberPlace(updateTeam.getNumberPlace());
                return teamRepo.save(team);
    }
    public Team validateTeam(Long teamId){
        Team team=getTeamById(teamId);
        team.setIsCreated(true);
        return teamRepo.save(team);}
    public Team updateTeam(Team team){return teamRepo.save(team);}
    public List<Team> getAllTeams(){return teamRepo.findAll();}
    public Team getTeamById(Long teamID){return teamRepo.findById(teamID).get();}
    public Team getTeamByName(String teamName){return teamRepo.getTeamByName(teamName);}
    public List<Team>getCreatedTeam(){return teamRepo.getTeamsByIsCreated(true);}
    public List<Team>getNotCreatedTeam(){return teamRepo.getTeamsByIsCreated(false);}
    public List<Team>getTeamsByCoach(Long coachID){return teamRepo.getTeamsByCoachId(coachID);}
    public List<Team> getPrivateTeams(){return teamRepo.getTeamsByIsPrivate(true);}
    public List<Team>getPublicTeams(){return teamRepo.getTeamsByIsPrivate(false);}
    public List<Team> getTeamsComplet(){return teamRepo.getTeamsByNumberPlaceEqualsAndIsCreatedTrue(0);}
    public List<Team>getTeamDispo(){return teamRepo.getTeamsByNumberPlaceGreaterThanAndIsCreatedTrue(0); }
    public List<Team>getTeamsNumberPlaceGreater(Integer numberPlace){
        return teamRepo.getTeamsByNumberPlaceGreaterThanEqualAndIsCreatedTrue(numberPlace);}
    public Team deleteTeam(Long teamId){
        Team team=getTeamById(teamId);
        team.setIsCreated(false);
        return teamRepo.save(team);}
    public List<Team> getTeamsByCoachComplet(Long coachId){return teamRepo.getTeamsByCoachIdAndNumberPlaceEqualsAndIsCreatedTrue(coachId,0);}
    public List<Team>getTeamsByCoachDispo(Long coachId){return teamRepo.getTeamsByCoachIdAndNumberPlaceGreaterThanAndIsCreatedTrue(coachId,0);}
    public List<Team>getTeamsByCaochMore(Long coachId,Integer x){return  teamRepo.getTeamsByCoachIdAndNumberPlaceGreaterThanEqualAndIsCreatedTrue(coachId,x);}

    public List<Team>getCreatedTeamByCoachId(Long coachId){return teamRepo.getTeamsByCoachIdAndIsCreated(coachId,true);}
    public List<Team>getNotCreatedTeamByCoachId(Long coachId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.hasRole("COACH")){
            return teamRepo.getTeamsByCoachIdAndIsCreated(user.getId(),false);
        }
        return teamRepo.getTeamsByCoachIdAndIsCreated(coachId,false);}


    //liste des id des team ayant au - un particpant n'est pas encore validé
    public List<Team>getTeamsNotAccepted(Long coachId){

            return teamRepo.getTeamsByCoachIdAndIdIn(coachId, userTeamService.getTeamIdsNotAccepted());
    }
}
