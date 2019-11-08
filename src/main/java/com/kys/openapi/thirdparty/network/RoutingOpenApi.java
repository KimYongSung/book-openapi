package com.kys.openapi.thirdparty.network;

import com.kys.openapi.app.exception.OpenApiCallFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

/**
 * OpenAPI Routing 처리
 */
@Slf4j
@Component
public class RoutingOpenApi {

    /**
     * api 호출
     * @param req 요청전문
     * @param main openAPI 최초 요청서버
     * @param backup openAPI backup 서버
     * @return
     */
    public <Req, Res> Res call(Req req, Function<Req, Res> main, Function<Req, Res> backup) {

        Res res = execute(req, main);

        if(Objects.nonNull(res)) return res;

        res = execute(req, backup);

        if(Objects.nonNull(res)) return res;

        throw new OpenApiCallFailException();
    }

    /**
     * OpenAPI 호출
     * @param req 요청전문
     * @param api API
     * @return
     */
    private <Req, Res> Res execute(Req req, Function<Req, Res> api){
        try{
            return api.apply(req);
        }catch(Exception e){
            log.error("{}", e.toString());
            return null;
        }
    }
}
