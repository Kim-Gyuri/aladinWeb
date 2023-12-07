package webservice.aladin.controller.api.dto.sort;

import lombok.Data;
import org.springframework.lang.Nullable;
import webservice.aladin.domain.enums.ItemSellStatus;
import webservice.aladin.domain.enums.OrderStatus;

@Data
public class ItemSearch {
    @Nullable
    private String itemName; // 상품명
    private ItemSellStatus itemStatus; // 판매 상태
    private OrderStatus orderStatus; //  주문 상태

}
