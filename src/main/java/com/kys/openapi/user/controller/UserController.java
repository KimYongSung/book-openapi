package com.kys.openapi.user.controller;

import com.kys.openapi.app.result.Response;
import com.kys.openapi.user.dto.UserDTO;
import com.kys.openapi.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 고객 정보 Controller
 */
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    /**
     * 회원 가입 처리
     * @param userDTO
     * @return
     */
    @PostMapping(value = "/join/user")
    public ResponseEntity<Response> joinUser(@Valid UserDTO userDTO){
        return ResponseEntity.ok(userService.joinUser(userDTO));
    }
    
}
