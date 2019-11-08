package com.kys.openapi.thirdparty.network;

import com.kys.openapi.app.exception.ThirdPartyApiCallFailException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.function.Function;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

@RunWith(MockitoJUnitRunner.class)
public class RoutingOpenApiTest {

    private RoutingOpenApi routingOpenApi = new RoutingOpenApi();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void 메인서버성공(){

        // given
        String req = "파라미터";
        Function<String, String> main = success();
        Function<String, String> back = success();

        // when
        String res = routingOpenApi.call(req, main, back);

        // then
        then(main).should().apply(eq(req));
        then(back).should(never()).apply(eq(req));
    }

    @Test
    public void 메인서버실패_백업서버성공(){

        // given
        String req = "파라미터";
        Function<String, String> main = error();
        Function<String, String> back = success();

        // when
        String res = routingOpenApi.call(req, main, back);

        // then
        then(main).should().apply(eq(req));
        then(back).should().apply(eq(req));
    }

    @Test
    public void 모두실패(){

        // given
        String req = "파라미터";
        Function<String, String> main = error();
        Function<String, String> back = error();

        // when
        try {
            String res = routingOpenApi.call(req, main, back);
            fail();
        }catch (ThirdPartyApiCallFailException e){
            // then
            then(main).should().apply(eq(req));
            then(back).should().apply(eq(req));
        }
    }

    public Function<String, String> success() {
        Function<String, String> function = mock(Function.class);
        given(function.apply(anyString())).willReturn("success");
        return function;
    }

    public Function<String, String> error() {
        Function<String, String> function = mock(Function.class);
        given(function.apply(anyString())).willThrow(RuntimeException.class);
        return function;
    }

}
