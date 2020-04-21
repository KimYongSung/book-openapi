package com.kys.openapi.user.service;

import com.kys.openapi.app.exception.AlreadyUserException;
import com.kys.openapi.app.exception.UserNotFoundException;
import com.kys.openapi.app.result.Response;
import com.kys.openapi.security.jwt.JwtTokenProvider;
import com.kys.openapi.user.domain.User;
import com.kys.openapi.user.domain.repository.UserRepository;
import com.kys.openapi.user.domain.repository.UserRepositorySupport;
import com.kys.openapi.user.dto.OpenApiUserDetail;
import com.kys.openapi.user.dto.TokenInfo;
import com.kys.openapi.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpenApiUserService implements UserService {

    private final UserRepository userRepository;

    private final UserRepositorySupport userRepositorySupport;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원 가입 처리
     * @param dto
     * @return
     */
    @Transactional
    @Override
    public Response joinUser(UserDTO dto){

        Optional<User> user = userRepositorySupport.findByUserId(dto.getUserId());

        if(user.isPresent())
            throw new AlreadyUserException();

        User newUser = dto.toEntity();

        newUser.encodePassword(encoder);

        userRepository.save(newUser);

        return Response.success();
    }

    /**
     * 로그인 처리
     * @param dto
     * @return
     */
    @Override
    public Response loginUser(UserDTO dto) {

        User user = userRepositorySupport.findByUserId(dto.getUserId())
                                         .orElseThrow(UserNotFoundException::new);

        OpenApiUserDetail userDetail = OpenApiUserDetail.of(user);

        Authentication authentication = userDetail.newAuthentication(dto);

        authenticationManager.authenticate(authentication);

        String token = jwtTokenProvider.createToken(userDetail.getUsername(), userDetail.getRoles());

        return Response.success(TokenInfo.valueOf(token));
    }
}
