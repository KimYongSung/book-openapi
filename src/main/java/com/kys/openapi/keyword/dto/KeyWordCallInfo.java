package com.kys.openapi.keyword.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class KeyWordCallInfo implements Comparable<KeyWordCallInfo> {

    private String keyWord;

    private int callCount;

    public KeyWordCallInfo(String keyWord, int callCount) {
        this.keyWord = keyWord;
        this.callCount = callCount;
    }

    public KeyWordCallInfo(String keyWord) {
        this.keyWord = keyWord;
        this.callCount = 0;
    }

    public KeyWordCallInfo update() {
        this.callCount++;
        return this;
    }

    @Override
    public int compareTo(KeyWordCallInfo keyWordCallInfo) {

        if (keyWordCallInfo.callCount == callCount) {
            return keyWord.compareTo(keyWordCallInfo.keyWord);
        }

        return keyWordCallInfo.callCount - this.callCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyWordCallInfo that = (KeyWordCallInfo) o;
        return callCount == that.callCount &&
                Objects.equals(keyWord, that.keyWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyWord, callCount);
    }
}
