package com.example.db_assignment.repository;

import com.example.db_assignment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, String> {
    @Modifying
    @Query("DELETE FROM Users u WHERE LOWER(u.name) LIKE %:substring1% OR LOWER(u.name) LIKE %:substring2%")
    int deleteUsersByNameContaining(@Param("substring1") String substring1, @Param("substring2") String substring2);
}
