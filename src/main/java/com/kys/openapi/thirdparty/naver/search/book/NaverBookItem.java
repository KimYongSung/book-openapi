package com.kys.openapi.thirdparty.naver.search.book;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class NaverBookItem {

    private String title;
    private String link;
    private String image;
    private String author;
    private Integer price;
    private Integer discount;
    private String publisher;
    private String isbn;
    private String description;
    private String pubdate;

    public List<String> getIsbns(){

        if(Objects.isNull(isbn)) return Collections.emptyList();

        return Arrays.asList(isbn.split(" "));
    }
}
