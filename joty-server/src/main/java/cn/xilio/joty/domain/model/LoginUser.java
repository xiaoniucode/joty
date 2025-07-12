package cn.xilio.joty.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser implements Serializable {
    private String uid;
    private String username;
    private String nickname;
    private String avatar;
    private String role;
    private String email;
}
