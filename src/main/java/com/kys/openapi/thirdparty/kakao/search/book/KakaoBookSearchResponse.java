package com.kys.openapi.thirdparty.kakao.search.book;

import com.kys.openapi.thirdparty.kakao.search.common.SearchMeta;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class KakaoBookSearchResponse {

    private SearchMeta meta;

    private List<KakaoBookSearchDocument> documents;

    public boolean isDocument(){
        return Objects.nonNull(documents) && documents.size() > 0;
    }

    /**
     * KakaoBookSearchDocument을 신규 Object로 변환
     * @param function
     * @param <T>
     * @return
     */
    public <T> List<T> convertDocument(Function<KakaoBookSearchDocument, T> function){
        return documents.stream()
                        .map(function)
                        .collect(Collectors.toList());
    }

    public boolean isNext(){
        return !meta.isEnd();
    }
}
