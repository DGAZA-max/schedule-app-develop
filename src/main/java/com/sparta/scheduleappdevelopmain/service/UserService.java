package com.sparta.scheduleappdevelopmain.service;


import com.sparta.scheduleappdevelopmain.dto.*;
import com.sparta.scheduleappdevelopmain.entity.User;
import com.sparta.scheduleappdevelopmain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        User user = new User(
                request.getEmail(),
                request.getPassword(),
                request.getNickname()
        );

        User savedUser = userRepository.save(user);
        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getNickname(),
                savedUser.getCreatedAt()
        );
    }


    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponse(user.getId(), user.getEmail());
    }


    public List<GetUserResponse> getAll() {
        List<User> userList = userRepository.findAll();
        List<GetUserResponse> dtos = new ArrayList<>();

        for (User user : userList){
            GetUserResponse dto = new GetUserResponse(user.getId(), user.getEmail(), user.getNickname());

            dtos.add(dto);
        }
        return dtos;
    }



    public GetUserResponse getOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));

        return new GetUserResponse(
                user.getId(),
                user.getNickname(),
                user.getNickname()
        );
    }



    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request){
        User user = userRepository.findById(userId).orElseThrow(
        () -> new IllegalStateException("없는 유저입니다.")
        );

        user.update(
                request.getEmail(),
                request.getPassword(),
                request.getNickname()
        );

        return new UpdateUserResponse(
                user.getEmail(),
                user.getPassword(),
                user.getNickname(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }


    public void delete(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("없는 유저입니다.")
                );

        userRepository.delete(user);
    }

}
