package com.kys.openapi.thirdparty.network;

import com.kys.openapi.app.constants.ErrorCode;
import com.kys.openapi.app.exception.OpenApiException;
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
     * API 호출
     * @return
     */
    public <Req, Res> Res call(Req req, Function<Req, Res> main, Function<Req, Res> backup) throws OpenApiException {

        Res res = execute(req, main);

        if(Objects.nonNull(res)) return res;

        res = execute(req, backup);

        if(Objects.nonNull(res)) return res;

        throw new OpenApiException(ErrorCode.CD_2000);
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
            log.warn("{}", e.toString());
            return null;
        }
    }
}
