package com.kys.openapi.user.service;

import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.exception.AlreadyUserException;
import com.kys.openapi.app.result.Response;
import com.kys.openapi.security.jwt.JwtTokenProvider;
import com.kys.openapi.user.domain.repository.UserRepository;
import com.kys.openapi.user.domain.repository.UserRepositorySupport;
import com.kys.openapi.user.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class OpenApiJoinUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRepositorySupport userRepositorySupport;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private OpenApiUserService userService;

    public UserDTO makeUser(){
        return UserDTO.builder()
                      .userPwd("test")
                      .userId("test")
                      .build();
    }

    @Test(expected = AlreadyUserException.class)
    public void 회원가입_이미_가입된_고객(){

        // given
        UserDTO userDTO = makeUser();

        given(userRepositorySupport.findByUserId(eq(userDTO.getUserId())))
                                   .willReturn(Optional.of(userDTO.toEntity()));

        // when
        userService.joinUser(userDTO);

    }

    @Test
    public void 회원가입처리(){

        // given
        UserDTO userDTO = makeUser();

        given(userRepositorySupport.findByUserIdAndUserPwd(eq(userDTO.getUserId()), eq(userDTO.getUserPwd())))
                .willReturn(Optional.empty());

        // when
        Response success = userService.joinUser(userDTO);

        // then
        then(userRepository).should().save(any());

        assertThat(success.getCode()).isEqualTo(ErrorCode.CD_0000.getCode());
    }
}
