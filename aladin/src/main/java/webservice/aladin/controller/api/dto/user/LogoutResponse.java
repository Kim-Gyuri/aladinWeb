package webservice.aladin.controller.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogoutResponse {
    private boolean success;
    private String message;
}
