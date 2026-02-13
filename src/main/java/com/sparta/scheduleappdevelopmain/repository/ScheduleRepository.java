package com.sparta.scheduleappdevelopmain.repository;

import com.sparta.scheduleappdevelopmain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List <Schedule> findAllByuserIdOrderByModifiedAtDesc(Long userId);
    List <Schedule> findAllByOrderByModifiedAtDesc();
}
