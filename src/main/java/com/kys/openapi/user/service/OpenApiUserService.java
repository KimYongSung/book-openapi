package com.kys.openapi.user.service;

import com.kys.openapi.app.exception.AlreadyUserException;
import com.kys.openapi.app.exception.UserNotFoundException;
import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.Response;
import com.kys.openapi.security.jwt.JwtTokenProvider;
import com.kys.openapi.user.domain.User;
import com.kys.openapi.user.domain.repository.UserRepository;
import com.kys.openapi.user.domain.repository.UserRepositorySupport;
import com.kys.openapi.user.dto.OpenApiUserDetail;
import com.kys.openapi.user.dto.TokenInfo;
import com.kys.openapi.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OpenApiUserService implements UserService {

    private UserRepository userRepository;

    private UserRepositorySupport userRepositorySupport;

    private PasswordEncoder encoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    /**
     * 회원 가입 처리
     * @param dto
     * @return
     */
    @Override
    public Response joinUser(UserDTO dto){

        Optional<User> user = userRepositorySupport.findByUserId(dto.getUserId());

        if(user.isPresent())
            throw new AlreadyUserException();

        dto.encodePassword(encoder);

        userRepository.save(dto.toEntity());

        return Response.success();
    }

    /**
     * 로그인 처리
     * @param dto
     * @return
     */
    @Override
    public DataResponse<TokenInfo> loginUser(UserDTO dto) {

        OpenApiUserDetail user = OpenApiUserDetail.of(userRepositorySupport.findByUserId(dto.getUserId())
                                                  .orElseThrow(UserNotFoundException::new));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), dto.getUserPwd()));

        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());

        return DataResponse.success(new TokenInfo(token));
    }
}
