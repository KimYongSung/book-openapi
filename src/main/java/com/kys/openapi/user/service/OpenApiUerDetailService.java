package com.kys.openapi.user.service;

import com.kys.openapi.app.exception.UserNotFoundException;
import com.kys.openapi.user.domain.User;
import com.kys.openapi.user.domain.repository.UserRepositorySupport;
import com.kys.openapi.user.dto.OpenApiUserDetail;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpenApiUerDetailService implements UserDetailsService {

    private UserRepositorySupport userRepositorySupport;

    /**
     * 유저 정보 검색
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepositorySupport.findByUserId(username)
                .orElseThrow(UserNotFoundException::new);

        return OpenApiUserDetail.of(user);
    }
}
