package com.kys.openapi.thirdparty.kakao.search.book;

import com.kys.openapi.thirdparty.kakao.search.common.SearchMeta;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class KakaoBookResponse {

    private SearchMeta meta;

    private List<KakaoBookDocument> documents;
}
