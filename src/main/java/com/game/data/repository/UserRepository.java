package com.game.data.repository;

import com.game.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserById(@Param("id") Integer id);

//    @Query("SELECT r FROM Role r WHERE r.name = :name")
//    Role findRoleByName(@Param("name") String name);
}
