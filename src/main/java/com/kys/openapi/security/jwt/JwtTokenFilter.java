package com.kys.openapi.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.result.Response;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtTokenFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;

    private ObjectMapper objectMapper;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, ObjectMapper objectMapper) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        try{

            String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);

            if (Objects.nonNull(token) && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            chain.doFilter(req, res);
        }catch(Exception e){
            exceptionHandler((HttpServletResponse) res);
        }
    }

    public void exceptionHandler(HttpServletResponse response)  {

        try{
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Response error = Response.error(ErrorCode.CD_S002);
            objectMapper.writeValue(response.getOutputStream(), error);

            logger.error(error);
        }catch (Exception e){
            logger.error("response send fail - " + e.toString());
        }
    }
}
