package com.kys.openapi.keyword.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SearchEventListenerTest {

    @Autowired
    private ApplicationEventPublisher publisher;

    @MockBean
    private SearchEventListener listener;

    @Test
    public void 이벤트수신_검증() {

        // given
        KeyWordEvent event = getKeyWordEvent();

        // when
        publisher.publishEvent(event);

        // then
        then(listener).should().eventListener(eq(event));
    }

    /**
     * 이벤트 객체 생성
     *
     * @return
     */
    private KeyWordEvent getKeyWordEvent() {
        return new KeyWordEvent() {
            @Override
            public Long userNo() {
                return 1l;
            }

            @Override
            public String keyWord() {
                return "kys0123";
            }
        };
    }
}
