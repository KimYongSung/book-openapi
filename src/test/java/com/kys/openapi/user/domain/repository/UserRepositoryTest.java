package com.kys.openapi.user.domain.repository;

import com.kys.openapi.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback
    public void USER_저장_테스트(){
        // given
        String id = "kys0213";
        String pwd = "kys0213";
        User user = new User(id, pwd);

        // when
        User newUser = userRepository.save(user);

        // then
        then(user).isEqualTo(newUser)
                  .hasFieldOrPropertyWithValue("userNo" , newUser.getUserNo());
    }
}
