package webservice.aladin.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import webservice.aladin.controller.api.dto.user.CreateUserResponse;
import webservice.aladin.controller.api.dto.user.GetUserResponse;
import webservice.aladin.controller.api.dto.user.LoginResponse;
import webservice.aladin.controller.api.dto.user.LogoutResponse;
import webservice.aladin.domain.dto.user.CreateUserRequest;
import webservice.aladin.domain.dto.user.LoginRequest;
import webservice.aladin.domain.entity.User;
import webservice.aladin.service.UserService;
import webservice.aladin.util.validation.argumentResolver.Login;
import webservice.aladin.util.validation.consts.SessionConst;
import webservice.aladin.util.validation.dto.SessionUser;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;

    // 회원 가입
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateUserResponse createUser(@Validated @RequestPart(value = "createUserRequest") CreateUserRequest dto) {
        Long id = userService.signUp(dto);

        User user = userService.findById(id);

        return new CreateUserResponse(user.getName(), user.getEmail(), user.getPassword());
    }

    // 로그인
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginResponse login(@Validated @RequestPart(value = "loginRequest") LoginRequest dto,
                               HttpServletRequest request) {
        User user = userService.signIn(dto);

        // 로그인 성공 처리
        // 세션이 있으면 세션 반환하고, 없으면 신규 세션을 반환한다.
        HttpSession session = request.getSession();
        // 세션이 로그인 정보를 보관한다.
        SessionUser sessionUser = new SessionUser(user);
        session.setAttribute(SessionConst.LOGIN_MEMBER, new SessionUser(user));
        return new LoginResponse(sessionUser.getLoginId());
    }


    // 로그아웃
    @PostMapping("/logout")
    public LogoutResponse logout(HttpServletRequest request) {
        // 세션을 없애기 위해 null을 반환하도록 false를 넣는다.
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // 세션 데이터 삭제
        }
        return new LogoutResponse(Boolean.TRUE, "logout success!");
    }

    // 회원 정보 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public GetUserResponse UserInfo(@Login SessionUser sessionUser) {
        User user = userService.findByLoginId(sessionUser.getLoginId());

        return new GetUserResponse(user);
    }
}
