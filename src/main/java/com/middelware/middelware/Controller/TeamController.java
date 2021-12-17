package com.middelware.middelware.Controller;

import com.middelware.middelware.Models.Team;
import com.middelware.middelware.Services.TeamService;
import com.middelware.middelware.dto.CreateTeam;
import com.middelware.middelware.dto.UpdateTeam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/Team")
@AllArgsConstructor
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeamController {
    @Autowired
    private TeamService teamService;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")//valid
    public List<Team> getAllTeams(){return teamService.getAllTeams();}
    @GetMapping("/{id}")//valid
    public Team getTeamById(@PathVariable Long id){return teamService.getTeamById(id);}
    @GetMapping("/byName/{name}")//valid
    public Team getTeamByName(@PathVariable String name){return teamService.getTeamByName(name);}
    @GetMapping("/createdTeam")//valid
    public List<Team> getCreatedTeams(){return teamService.getCreatedTeam();}
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/notCreatedTeam")//valid
    public List<Team> getNotCreatedTeams(){return teamService.getNotCreatedTeam();}

    @GetMapping("/privateTeam")//valid
    public List<Team> getPrivateTeams(){return teamService.getPrivateTeams();}

    @GetMapping("/publicTeam")//valid
    public List<Team> getPublicTeams(){return teamService.getPublicTeams();}

    @GetMapping("/byCoachId/{coachId}")//valid
    public List<Team>getTeamsByCoachId(@PathVariable Long coachId){return teamService.getTeamsByCoach(coachId);}

    @GetMapping("/teamCompletByCoachId/{coachId}")//valid
    public List<Team>getTeamsCompletByCoachId(@PathVariable Long coachId){return teamService.getTeamsByCoachComplet(coachId);}

    @GetMapping("/teamDispoByCoachId/{coachId}")//valid
    public List<Team>getTeamsDispoByCoachId(@PathVariable Long coachId){return teamService.getTeamsByCoachDispo(coachId);}

    @GetMapping("/teamMoreByCoachId/{coachId}/{min}")//valid
    public List<Team>getTeamsMoreByCoachId(@PathVariable Long coachId,@PathVariable Integer min){
        return teamService.getTeamsByCaochMore(coachId,min);}

    @GetMapping("/createdTeamByCoachId/{coachId}")//valid
    public List<Team>getCreatedTeamByCoachId(@PathVariable Long coachId){return teamService.getCreatedTeamByCoachId(coachId);}

    //@PreAuthorize("hasAnyRole('ADMIN','COACH')")
    @GetMapping("/notCreatedTeamByCoachId/{coachId}")//valid
    public List<Team>getNotCreatedTeamByCoachId(@PathVariable Long coachId){return teamService.getNotCreatedTeamByCoachId(coachId);}

    @GetMapping("/completTeam")//valid
    public List<Team> getCompletTeams(){return teamService.getTeamsComplet();}

    @GetMapping("/dispoTeam")//valid
    public List<Team> getDipoTeams(){return teamService.getTeamDispo();}

    @GetMapping("/moreThanTeam/{min}")//valid
    public List<Team> getMoreThanTeams(@PathVariable  Integer min){return teamService.getTeamsNumberPlaceGreater(min);}

    //@PreAuthorize("hasRole('COACH')")
    @GetMapping("/notAcceptedTeamParticipated/{coachId}")
    public List<Team> getNotAcceptedTeamParticipated(@PathVariable Long coachId){return teamService.getTeamsNotAccepted(coachId);}

    //@PreAuthorize("hasAnyRole('ADMIN','COACH')")
    @PostMapping("")//valid
    public Team createTeam(@RequestBody CreateTeam createTeam) {return teamService.createTeam(createTeam);}

    //@PreAuthorize("hasRole('COACH')")
    @PutMapping("")//valid
    public Team updateTeamCoach(@RequestBody UpdateTeam updateTeam) {return teamService.updateTeamCoach(updateTeam);}

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin")//valid
    public Team updateTeamAdmin(@RequestBody Team team) {return teamService.updateTeam(team);}

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/validateTeam/{teamId}")//valid
    public Team validateTeam(@PathVariable Long teamId) {
        return teamService.validateTeam(teamId);
    }

}
