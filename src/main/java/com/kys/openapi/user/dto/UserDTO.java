package com.kys.openapi.user.dto;

import com.kys.openapi.user.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UserDTO {

    @NotEmpty
    @Length(max = 64)
    private String userId;

    @NotEmpty
    @Length(max = 30)
    private String userPwd;

    @Builder
    public UserDTO(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }

    public User toEntity(){
        return User.builder()
                   .userId(userId)
                   .userPwd(userPwd)
                   .build();
    }
}
