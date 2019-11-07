package com.kys.openapi.thirdparty.naver.search.book;

import com.kys.openapi.thirdparty.config.QueryString;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 책 상세 검색
 */
@ToString
@NoArgsConstructor
@Getter
public class NaverBookDetailSearchRequest implements QueryString {

    private String dTitl;

    private String dAuth;

    private String dCont;

    private String dIsbn;

    private String dPubl;

    private String dDafr;

    private String dDato;

    private String dCatg;

    @Builder
    public NaverBookDetailSearchRequest(String dTitl, String dAuth, String dCont, String dIsbn, String dPubl,
                                        String dDafr, String dDato, String dCatg) {
        this.dTitl = dTitl;
        this.dAuth = dAuth;
        this.dCont = dCont;
        this.dIsbn = dIsbn;
        this.dPubl = dPubl;
        this.dDafr = dDafr;
        this.dDato = dDato;
        this.dCatg = dCatg;
    }

    @Override
    public String toUrl(UriComponentsBuilder builder) {
        addParam(builder, "d_titl", dTitl);
        addParam(builder, "d_auth", dAuth);
        addParam(builder, "d_cont", dCont);
        addParam(builder, "d_isbn", dIsbn);
        addParam(builder, "d_publ", dPubl);
        addParam(builder, "d_dafr", dDafr);
        addParam(builder, "d_dato", dDato);
        addParam(builder, "d_catg", dCatg);
        return builder.build().toUriString();
    }
}
