package com.middelware.middelware.Repo;

import com.middelware.middelware.Models.User;
import com.middelware.middelware.Models.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTeamRepo extends JpaRepository<UserTeam,Long> {
    List<UserTeam> getUserTeamsByTeamIdAndIsAcceptedTrue(Long teamId);
    List<UserTeam> getUserTeamsByTeamIdAndIsAcceptedFalse(Long teamId);
    List<UserTeam>getUserTeamsByUserParticipatedIdAndIsAcceptedTrue(Long userId);
    List<UserTeam>getUserTeamsByUserParticipatedIdAndIsAcceptedFalse(Long userId);
    UserTeam getUserTeamByTeamIdAndUserParticipatedId(Long teamId,Long userParticipatedId);
    @Query(value = "SELECT DISTINCT team_id FROM user_team WHERE is_accepted = false; ", nativeQuery = true)
    List<Long> getTeamsNotAcceptedIds();
}
