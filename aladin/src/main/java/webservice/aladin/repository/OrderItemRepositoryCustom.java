package webservice.aladin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webservice.aladin.domain.dto.orderItem.GetOrderItemResponse;
import webservice.aladin.domain.enums.OrderStatus;

import java.util.List;

public interface OrderItemRepositoryCustom {
  // 회원의 장바구니 아이디 번호로 장바구니에 담긴 주문상품 정보조회
  List<GetOrderItemResponse> findAllByCart_id(Long id);
  List<GetOrderItemResponse> findAllBySellerId(String id);
  Page<GetOrderItemResponse> findAllBySellerId_paging(String id, OrderStatus orderStatus, Pageable pageable);


}
