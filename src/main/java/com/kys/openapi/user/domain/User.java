package com.kys.openapi.user.domain;

import com.kys.openapi.user.code.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 회원 정보
 */
@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "USER", indexes = {@Index(name = "IDX_BOOK_USER_01", columnList = "USER_ID")})
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_NO", unique = true)
    private Long userNo;

    @Column(name = "USER_ID", length = 64, nullable = false)
    private String userId;

    @Column(name = "USER_PWD", length = 256, nullable = false)
    private String userPwd;

    @Column(name = "USER_STATUS", length = 10)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name="REG_DT")
    private LocalDateTime regDt;

    @Column(name="CHG_DT")
    private LocalDateTime chgDt;

    @Builder
    public User(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.status = UserStatus.JOIN;
        this.regDt = LocalDateTime.now();
    }

    /**
     * 비밀번호 암호화
     * @param encoder {@link PasswordEncoder} 구현체
     */
    public void encodePassword(PasswordEncoder encoder){
        this.userPwd = encoder.encode(this.userPwd);
    }

    /**
     * 회원 상태 변경
     */
    public void closeUser(){
        this.status = UserStatus.CLOSE;
    }

    /**
     * 수정일 변경
     */
    public void updateChgDt(){
        this.chgDt = LocalDateTime.now();
    }
}
