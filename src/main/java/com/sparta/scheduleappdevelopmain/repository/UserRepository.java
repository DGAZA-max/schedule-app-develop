package com.sparta.scheduleappdevelopmain.repository;


import com.sparta.scheduleappdevelopmain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 자바와 DB 사이의 통역사


    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
