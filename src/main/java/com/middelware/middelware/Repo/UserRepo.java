package com.middelware.middelware.Repo;

import com.middelware.middelware.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {
User getUserByEmail(String email);
List<User> getUsersByFirstName(String firstName);
List<User> getUsersByLastName(String lastName);
User getUserByCin(Long cin);
}
