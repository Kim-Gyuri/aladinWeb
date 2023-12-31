package webservice.aladin.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webservice.aladin.domain.dto.sales.GetSalesResponse;
import webservice.aladin.service.SalesService;
import webservice.aladin.util.validation.argumentResolver.Login;
import webservice.aladin.util.validation.dto.SessionUser;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class SalesApiController {

    private final SalesService salesService;

    // 회원이 현재 판매하는 상품정보/총 판매액 조회
    @GetMapping("/sales")
    public GetSalesResponse myItems(@Login SessionUser user) {
        return salesService.findItemByUserLoginId(user.getLoginId());
    }
}
