package com.middelware.middelware.Controller;

import com.middelware.middelware.Models.*;
import com.middelware.middelware.Services.UserTeamService;
import com.middelware.middelware.dto.CreateParticipatedTeam;
import com.middelware.middelware.dto.CreateUserTeam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/UserTeam")
@AllArgsConstructor
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserTeamController {
    @Autowired
    private UserTeamService userTeamService;

   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public List<UserTeam>getAllUserTeams(){return userTeamService.getAllUserTeams();}

    @GetMapping("/{id}")//valid
    public UserTeam getUserTeamById(@PathVariable Long id) {
        return userTeamService.getUserTeamById(id);
    }

    @GetMapping("/participatedByTeam/{team_id}/{currentUser}")
    public List<User> getParticipatedByTeam(@PathVariable Long team_id,@PathVariable Long currentUser){
        return userTeamService.getParticipatedByTeam(team_id,currentUser);}

    //@PreAuthorize("hasRole('COACH')")
    @GetMapping("/notParticipatedByTeam/{team_id}/{currentUser}")
    public List<User> getNotParticipatedByTeam(@PathVariable Long team_id,@PathVariable Long currentUser){
        return userTeamService.getNotParticipatedByTeam(team_id,currentUser);}

    @GetMapping("/numberParticipatedByTeam/{team_id}/{currentUser}")
    public Integer getNumberParticipatedByTeam(@PathVariable Long team_id,@PathVariable Long currentUser){
        return userTeamService.numberParticipatedTeam(team_id,currentUser);}

    //@PreAuthorize("hasRole('COACH')")
    @GetMapping("/numberNotParticipatedByTeam/{team_id}/{currentUser}")
    public Integer getNumberNotParticipatedByTeam(@PathVariable Long team_id,@PathVariable Long currentUser){
        return userTeamService.numberNotParticipatedTeam(team_id,currentUser);}

    @GetMapping("/teamsAcceptedByClient/{currentUser}")
    public List<Team> getTeamsAcceptedByClient(@PathVariable Long currentUser){
        return userTeamService.getTeamsByUSer(currentUser);}

    @GetMapping("/numberTeamsAcceptedByClient/{currentUser}")
    public Integer getNumberTeamsAcceptedByClient(@PathVariable Long currentUser){
        return userTeamService.numberTeams(currentUser);}

    @GetMapping("/teamsNotAcceptedByClient/{currentUser}")
    public List<Team> getTeamsNotAcceptedByClient(@PathVariable Long currentUser){
        return userTeamService.getTeamsNotAcceptedByUSer(currentUser);}

    @GetMapping("/numberTeamsNotAcceptedByClient/{currentUser}")
    public Integer getNumberTeamsNotAcceptedByClient(@PathVariable Long currentUser){
        return userTeamService.numberTeamsNotAccepted(currentUser);}


    @PostMapping("")
    public UserTeam participate(@RequestBody CreateUserTeam createUserTeam)
    {return userTeamService.participateUserTeam(createUserTeam);}

    //@PreAuthorize("hasRole('COACH')")
    @PostMapping("/listMail")
    public List<UserTeam> createUserTeams(@RequestBody CreateParticipatedTeam createParticipatedTeam){
        return userTeamService.createParticpatedUserTeams(createParticipatedTeam);}

    //@PreAuthorize("hasRole('COACH')")
    @PutMapping("/validate/{userTeamId}/{currentUser}")
    public UserTeam validateUserTeam(@PathVariable Long userTeamId,@PathVariable Long currentUser){
        return userTeamService.validateParticipant(userTeamId,currentUser);}

   // @PreAuthorize("hasRole('COACH')")
    @PutMapping("/delete/{userTeamId}/{currentUser}")
    public UserTeam deleteUserTeam(@PathVariable Long userTeamId,@PathVariable Long currentUser){
        return userTeamService.deleteUserTeam(userTeamId,currentUser);}

    @PutMapping("/leave/{teamId}/{currentUser}")
    public void leave(@PathVariable Long teamId,@PathVariable Long currentUser){
        userTeamService.leave(teamId,currentUser);}
}
