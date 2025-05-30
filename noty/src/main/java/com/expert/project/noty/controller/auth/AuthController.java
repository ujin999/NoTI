package com.expert.project.noty.controller.auth;

import com.expert.project.noty.dto.auth.*;
import com.expert.project.noty.service.auth.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        boolean result = userService.register(request);

        if (result) {
            return ResponseEntity.ok(new RegisterResponse("회원가입 성공"));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RegisterResponse("이미 존재하는 사용자 ID입니다."));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String result = userService.login(request);

        if (result.equals("로그인 성공")) {
            return ResponseEntity.ok(new LoginResponse("로그인 성공"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("로그인 실패"));
        }
    }

    @PostMapping("/modify/password")
    public ResponseEntity<ModifyResponse> modify(@RequestBody ModifyRequest request) {
        String result = userService.modify(request);

        if (result.equals("비밀번호 변경 성공")) {
            return ResponseEntity.ok(new ModifyResponse("비밀번호 변경 성공"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ModifyResponse("비밀번호 변경 실패"));
        }
    }

    @PostMapping("/update/user-information")
    public ResponseEntity<String> modifyUserInfo(@ModelAttribute UpdateUserRequest request) {
        boolean success = userService.updateUserInfo(request);

        if (success) {
            return ResponseEntity.ok("사용자 정보 수정 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("사용자 정보 수정 실패");
        }
    }

    @PostMapping("/get/user-information")
    public ResponseEntity<UserInfoResponse> getUserInfo() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        UserInfoResponse userResponse = userService.getUserById(userId);

        return ResponseEntity.ok(userResponse);
    }


}
