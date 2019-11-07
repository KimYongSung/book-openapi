package com.kys.openapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

    private String token;

    public static TokenInfo valueOf(String token){
        return new TokenInfo(token);
    }
}
