package com.kys.openapi.user.domain.repository;

import com.kys.openapi.exception.user.UserNotFoundException;
import com.kys.openapi.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindUserIdAndPwdTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositorySupport userRepositorySupport;

    @Test
    public void ID와_PWD로_고객정보_조회(){
        // given
        String id = "kys0213";
        String pwd = "kys0213";
        User user = new User(id, pwd);
        userRepository.save(user);

        // when
        Optional<User> searchUser = userRepositorySupport.findByUserIdAndUserPwd(id, pwd);

        // then
        then(searchUser.isPresent()).isEqualTo(true);
    }

    @Test
    public void 비정상_ID로_고객정보_조회(){
        // given
        String id = "kys0213";
        String pwd = "kys0213";
        User user = new User(id, pwd);
        userRepository.save(user);

        // when
        Optional<User> searchUser =userRepositorySupport.findByUserIdAndUserPwd("kys0245", "kys0213");

        // then
        then(searchUser.isPresent()).isEqualTo(false);
    }

    @Test
    public void 비정상_PWD로_고객정보_조회(){
        // given
        String id = "kys0213";
        String pwd = "kys0213";
        User user = new User(id, pwd);
        userRepository.save(user);

        // when
        Optional<User> searchUser =userRepositorySupport.findByUserIdAndUserPwd("kys0213", "kys0245");

        // then
        then(searchUser.isPresent()).isEqualTo(false);
    }
}
