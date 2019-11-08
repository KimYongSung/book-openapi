package com.kys.openapi.thirdparty.naver.search.book;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@XmlRootElement(name = "rss")
@XmlAccessorType(XmlAccessType.FIELD)
public class NaverBookSearchResponse {

    private Channel channel;

    @Getter
    @XmlRootElement(name = "channel")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Channel{

        private String lastBuildDate;

        private Integer total;

        private Integer start;

        private Integer display;

        @XmlElement(name = "item")
        private List<NaverBookItem> items;

        public boolean isItems(){
            return Objects.nonNull(items) && items.size() > 0;
        }

        /**
         * NaverBookItem을 신규 VO로 변환
         * @param function
         * @param <T>
         * @return
         */
        public <T> List<T> convertItem(Function<NaverBookItem, T> function){
            return items.stream()
                        .map(function)
                        .collect(Collectors.toList());
        }
    }

}

