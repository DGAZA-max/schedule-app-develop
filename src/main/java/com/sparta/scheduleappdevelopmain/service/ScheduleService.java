package com.sparta.scheduleappdevelopmain.service;


import com.sparta.scheduleappdevelopmain.dto.*;
import com.sparta.scheduleappdevelopmain.entity.Schedule;
import com.sparta.scheduleappdevelopmain.entity.User;
import com.sparta.scheduleappdevelopmain.repository.ScheduleRepository;
import com.sparta.scheduleappdevelopmain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), user);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getUser().getId(),
                savedSchedule.getCreatedAt()
        );
    }



    public GetScheduleResponse getOne(Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("없는 일정입니다.")
                );
        return new GetScheduleResponse(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }



    public List<GetScheduleResponse> getAll(Long userId){
        List<Schedule> schedules;
        if (userId != null){
            schedules = scheduleRepository.findAllByuserIdOrderByModifiedAtDesc(userId);
        } else {
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        }
        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules){
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }



    @Transactional
    public UpdateScheduleResponse update(Long UserId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(UserId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );

        if (!schedule.getUser().getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(
                request.getTitle(),
                request.getContent()
        );

        return new UpdateScheduleResponse(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }


    @Transactional
    public void delete(Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("없는 일정입니다."));

        scheduleRepository.delete(schedule);
    }
}
