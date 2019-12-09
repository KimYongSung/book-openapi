package com.kys.openapi.user.dto;

import com.kys.openapi.user.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UserDTO {

    @NotEmpty(message = "ID가 누락되었습니다.")
    @Length(max = 64, message = "ID는 64자리수까지 가능합니다.")
    private String userId;

    @NotEmpty(message = "비밀번호가 누락되었습니다.")
    @Length(max = 30, message = "비밀번호는 30자리수까지 가능합니다.")
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
