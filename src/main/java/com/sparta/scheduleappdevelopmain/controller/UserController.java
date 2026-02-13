package com.sparta.scheduleappdevelopmain.controller;


import com.sparta.scheduleappdevelopmain.dto.*;
import com.sparta.scheduleappdevelopmain.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = userService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session){
        LoginResponse response = userService.login(request);
        SessionUser sessionUser = new SessionUser(response.getId(), response.getEmail());
        session.setAttribute("LoginUser", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, HttpSession session){
        if (sessionUser == null){
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }



    @GetMapping("/users/{userid}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable Long userId) {
        GetUserResponse getOne = userService.getOne(userId);
        return ResponseEntity.status(HttpStatus.OK).body(getOne);
    }



    @PutMapping("/users/{userid}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Long userid, @RequestBody UpdateUserRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userid, request));
    }



    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId){
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).body("삭제 되었습니다.");
    }
}


