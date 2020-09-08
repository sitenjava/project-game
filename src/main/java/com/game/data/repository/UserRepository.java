package com.game.data.repository;

import com.game.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM User " +
            "JOIN User_Role ON User.id = User_Role.user_id " +
            "JOIN Role ON User_Role.role_id = Role.id " +
            "WHERE Role.name = :roleName", nativeQuery = true)
    List<User> findUsersByRole(@Param("roleName") String name);

    @Query("SELECT u FROM User u WHERE u.active = :active")
    List<User> findUserByStatus(@Param("active") boolean active);
}
