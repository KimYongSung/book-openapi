package com.kys.openapi.user.service;

import com.kys.openapi.app.exception.AlreadyUserException;
import com.kys.openapi.app.result.Response;
import com.kys.openapi.user.domain.User;
import com.kys.openapi.user.domain.repository.UserRepository;
import com.kys.openapi.user.domain.repository.UserRepositorySupport;
import com.kys.openapi.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OpenApiUserService implements UserService{

    private UserRepository userRepository;

    private UserRepositorySupport userRepositorySupport;

    /**
     * 회원 가입 처리
     * @param dto
     * @return
     */
    @Override
    public Response joinUser(UserDTO dto){

        Optional<User> user = userRepositorySupport.findByUserIdAndUserPwd(dto.getUserId(), dto.getUserPwd());

        if(user.isPresent())
            throw new AlreadyUserException();

        userRepository.save(dto.toEntity());

        return Response.success();
    }
}
