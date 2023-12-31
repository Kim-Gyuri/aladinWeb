package webservice.aladin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webservice.aladin.domain.dto.user.CreateUserRequest;
import webservice.aladin.domain.dto.user.LoginRequest;
import webservice.aladin.domain.entity.Cart;
import webservice.aladin.domain.entity.Sales;
import webservice.aladin.domain.entity.User;
import webservice.aladin.repository.CartRepository;
import webservice.aladin.repository.ItemRepository;
import webservice.aladin.repository.SalesRepository;
import webservice.aladin.repository.UserRepository;
import webservice.aladin.util.exception.user.DuplicateLoginIdException;
import webservice.aladin.util.exception.user.NotFoundUserException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final SalesRepository salesRepository;

    // 회원가입
    public Long signUp(CreateUserRequest userFormDto) {
        validateDuplicateUser(userFormDto.getLoginId());
        User user = userRepository.save(userFormDto.userEntity());
        user.createCart(cartRepository.save(new Cart()));
        user.createSales(salesRepository.save(new Sales()));

        return user.getId();
    }

    // 중복 회원 체크
    @Transactional(readOnly = true)
    private void validateDuplicateUser(String loginId) {
        Optional<User> byLoginId = userRepository.findByLoginId(loginId);
        if (byLoginId.isPresent()) {
            throw new DuplicateLoginIdException("이미 존재하는 아이디입니다.");
        }
    }

    // 로그인 시도 -> 존재하는 회원 정보인지 확인한다. (로그인ID, 비밀번호 확인)
    @Transactional(readOnly = true)
    public void existByLoginIdAndPassword(LoginRequest loginFormDto) {
        if (!userRepository.existsByLoginIdAndPassword(loginFormDto.getLoginId(), loginFormDto.getPassword())) {
            throw new NotFoundUserException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    // 로그인
    public User signIn(LoginRequest loginFormDto) {
        existByLoginIdAndPassword(loginFormDto);
        return userRepository.findByLoginId(loginFormDto.getLoginId())
                .filter(m -> m.getPassword().equals(loginFormDto.getPassword()))
                .orElse(null);
    }


    // (id 번호로 회원 찾기)
    @Transactional(readOnly = true)
    public User findById(long id) {
        return userRepository.findById(id);
    }


    // 로그인 ID로 회원정보 찾기
    @Transactional(readOnly = true)
    public User findByLoginId(String id) {
        return userRepository.findByLoginId(id)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자입니다."));
    }



}
