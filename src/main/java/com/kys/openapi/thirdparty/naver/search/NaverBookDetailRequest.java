package com.kys.openapi.thirdparty.naver.search;

import com.kys.openapi.thirdparty.network.QueryString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class NaverBookDetailRequest implements QueryString {

    private String dTitl;

    private String dAuth;

    private String dCont;

    private String dIsbn;

    private String dBubl;

    private String dDafr;

    private String dDato;

    private String dCatg;

    @Override
    public String toQueryString() {
        return null;
    }
}
