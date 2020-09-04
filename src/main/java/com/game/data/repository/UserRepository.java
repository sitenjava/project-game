package com.game.data.repository;

import com.game.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(@Param("username") String username);
//    @Query("SELECT r FROM Role r WHERE r.name = :name")
//    Role findRoleByName(@Param("name") String name);
//    Optional<User> findById(Integer id);
}
