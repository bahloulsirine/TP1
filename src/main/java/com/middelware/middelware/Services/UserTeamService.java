package com.middelware.middelware.Services;

import com.middelware.middelware.Models.*;
import com.middelware.middelware.Repo.TeamRepo;
import com.middelware.middelware.Repo.UserTeamRepo;
import com.middelware.middelware.dto.CreateParticipatedTeam;
import com.middelware.middelware.dto.CreateUserTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Transactional
public class UserTeamService {
    @Autowired
    private UserTeamRepo userTeamRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamRepo teamRepo;

    public UserTeam participateUserTeam(CreateUserTeam createUserTeam){
        Team team=teamService.getTeamById(createUserTeam.getTeamId());
        if((team.getIsCreated()==true)&&(team.getNumberPlace()>0)) {
            User user = userService.getUserById(createUserTeam.getUserId());
            UserTeam userTeam=userTeamRepo.getUserTeamByTeamIdAndUserParticipatedId(team.getId(),user.getId());
            if (userTeam==null) {
                UserTeam newUserTeam = new UserTeam(null, false, user, team);
                return userTeamRepo.save(newUserTeam);
            }
        }
        return null;
    }

    public List<UserTeam> createParticpatedUserTeams(CreateParticipatedTeam createParticipatedTeam){
        User coach = userService.getUserById(createParticipatedTeam.getUserId());
        List<Team>teams=teamService.getTeamsByCoach(coach.getId());
        Team team=teamService.getTeamById(createParticipatedTeam.getTeamId());
        if(teams.contains(team)){
            List<String> mails=createParticipatedTeam.getMails();
            if((team.getNumberPlace()>= mails.size())&&(team.getIsCreated()==true)) {
                List<UserTeam>userTeams=new ArrayList<>();
                for (String mail : createParticipatedTeam.getMails()) {
                    User user = userService.getUserByEmail(mail);
                    UserTeam userTeam=userTeamRepo.getUserTeamByTeamIdAndUserParticipatedId(team.getId(),user.getId());
                    if (userTeam==null) {
                        team.setNumberPlace(team.getNumberPlace() - 1);
                        teamRepo.save(team);
                        UserTeam newUserTeam = new UserTeam(null, true, user, team);
                        userTeams.add(newUserTeam);
                        userTeamRepo.save(newUserTeam);
                    }
                    else if (userTeam.getIsAccepted()==false){
                        team.setNumberPlace(team.getNumberPlace() - 1);
                        teamRepo.save(team);
                        userTeam.setIsAccepted(true);
                        userTeamRepo.save(userTeam);
                        userTeams.add(userTeam);
                    }
                }
                return userTeams;

        }}
        return null;
    }
    public UserTeam getUserTeamById(Long id){return userTeamRepo.findById(id).get();}
    public List<User> getParticipatedByTeam(Long teamId,Long currentUser){
        User user = userService.getUserById(currentUser);
        if(user.hasRole("ADMIN")) {
            return getParticipated(teamId);
        }
        else if (user.hasRole("COACH")){
            List<Team>teams=teamService.getTeamsByCoach(user.getId());

            if(teams.contains(teamService.getTeamById(teamId))){
                return getParticipated(teamId);
            }}
        List<Team> myTeams=getTeamsByUSer(user.getId());
        if (myTeams.contains(teamService.getTeamById(teamId))){
            return getParticipated(teamId);
        }
        return null;
    }
    public List<User>getParticipated(Long teamId){
        List<UserTeam> userTeams=userTeamRepo.getUserTeamsByTeamIdAndIsAcceptedTrue(teamId);
        System.out.println(userTeams);
        List<User> users=new ArrayList<>();
        for (UserTeam userTeam :userTeams){
            users.add(userTeam.getUserParticipated());
        }
        return users;
    }
    public List<User> getNotParticipatedByTeam(Long teamId,Long currentUser){
        User user = userService.getUserById(currentUser);
        List<Team>teams=teamService.getTeamsByCoach(user.getId());
        if(teams.contains(teamService.getTeamById(teamId))){
            List<UserTeam> userTeams=userTeamRepo.getUserTeamsByTeamIdAndIsAcceptedFalse(teamId);
            List<User> users=new ArrayList<>();
            for (UserTeam userTeam :userTeams){
                users.add(userTeam.getUserParticipated());}
            return users;
        }
        return null;}
    public Integer numberParticipatedTeam(Long teamId,Long currentUser){
        return getParticipatedByTeam(teamId,currentUser).size();}
    public Integer numberNotParticipatedTeam(Long teamId,Long currentUser){
        return getNotParticipatedByTeam(teamId,currentUser).size();}


    public List<Team> getTeamsByUSer(Long currentUser){
        User user = userService.getUserById(currentUser);
        List<UserTeam> userTeams=userTeamRepo.getUserTeamsByUserParticipatedIdAndIsAcceptedTrue(user.getId());
        List<Team> teams=new ArrayList<>();
        for (UserTeam userTeam:userTeams){
            teams.add(userTeam.getTeam());}
        return  teams;}

    public  Integer numberTeams(Long currentUser){
        return getTeamsByUSer(currentUser).size(); }

    public List<Team> getTeamsNotAcceptedByUSer(Long currentUser){
        User user =userService.getUserById(currentUser);
        List<UserTeam> userTeams=userTeamRepo.getUserTeamsByUserParticipatedIdAndIsAcceptedFalse(user.getId());
        List<Team> teams=new ArrayList<>();
        for (UserTeam userTeam:userTeams){
            teams.add(userTeam.getTeam());}
        return  teams;}
    public  Integer numberTeamsNotAccepted(Long currentUser){
        return getTeamsNotAcceptedByUSer(currentUser).size(); }
    public List<UserTeam> getAllUserTeams(){return userTeamRepo.findAll();}
//doit avoir coach connecté --> 1L
    public UserTeam validateParticipant(Long userTeamId,Long currentUser){
        UserTeam userTeam=getUserTeamById(userTeamId);
        Team team=userTeam.getTeam();
        User user = userService.getUserById(currentUser);
        List<Team>teams=teamService.getTeamsByCoach(user.getId());
        if (teams.contains(team)){
            if(team.getNumberPlace()>0){
                team.setNumberPlace(team.getNumberPlace()-1);
                teamRepo.save(team);
                userTeam.setIsAccepted(true);
                return userTeamRepo.save(userTeam);}}
        return null;}
    public UserTeam deleteUserTeam(Long userTeamId,Long currentUser){
        UserTeam userTeam=getUserTeamById(userTeamId);
        Team team=userTeam.getTeam();
        User user = userService.getUserById(currentUser);
        List<Team>teams=teamService.getTeamsByCoach(user.getId());
        if (teams.contains(team)){
        userTeam.setIsAccepted(false);
        return userTeamRepo.save(userTeam);}
        return null;}
    //usr connecté
    public void leave(Long teamId,Long currentUser){
        User user = userService.getUserById(currentUser);
        UserTeam userTeam=userTeamRepo.getUserTeamByTeamIdAndUserParticipatedId(teamId,user.getId());
        if(userTeam!=null){
            userTeamRepo.deleteById(userTeam.getId());
        }
    }

    List<Long>getTeamIdsNotAccepted(){
        return userTeamRepo.getTeamsNotAcceptedIds();}


}
