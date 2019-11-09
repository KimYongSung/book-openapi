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
    @Length(max = 64)
    private String userId;

    @NotEmpty(message = "비밀번호가 누락되었습니다.")
    @Length(max = 30)
    private String userPwd;

    @Builder
    public UserDTO(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }

    public void encodePassword(PasswordEncoder encoder){
        this.userPwd = encoder.encode(this.userPwd);
    }

    public User toEntity(){
        return User.builder()
                   .userId(userId)
                   .userPwd(userPwd)
                   .build();
    }
}
