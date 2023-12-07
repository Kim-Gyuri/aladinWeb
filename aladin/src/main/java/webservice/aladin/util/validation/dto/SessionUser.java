package webservice.aladin.util.validation.dto;

import lombok.Getter;
import webservice.aladin.domain.entity.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String loginId;

    public SessionUser(User user) {
        this.loginId = user.getLoginId();
    }
}
