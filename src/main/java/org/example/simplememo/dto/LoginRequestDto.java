package org.example.simplememo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.simplememo.entity.User;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
