package com.kys.openapi.security.jwt;

import com.kys.openapi.security.exception.InvalidJwtAuthenticationException;
import com.kys.openapi.user.code.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;


@RunWith(MockitoJUnitRunner.class)
public class JwtTokenProviderTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Spy
    @InjectMocks
    private JwtTokenProvider provider;

    @Before
    public void setUp(){
        provider.setSecret("aqajiavjlaisdf");
        provider.setValidity(3000l);
    }

    @Test
    public void 토큰생성_및_검증(){

        String token = provider.createToken("1", Arrays.asList(Role.USER.name()));
        provider.validateToken(token);
    }

    @Test(expected = InvalidJwtAuthenticationException.class)
    public void 토큰생성_만료시간_검증() throws InterruptedException {

        String token = provider.createToken("1", Arrays.asList(Role.USER.name()));

        Thread.sleep(5000l);

        provider.validateToken(token);
    }

    @Test(expected = InvalidJwtAuthenticationException.class)
    public void 비정상토큰_검증(){

        provider.validateToken("asdjjioajsdljfojwefojasdiofjioweasf");
    }
}
