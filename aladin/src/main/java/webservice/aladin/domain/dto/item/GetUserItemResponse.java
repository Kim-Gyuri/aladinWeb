package webservice.aladin.domain.dto.item;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import webservice.aladin.domain.enums.CategoryType;
import webservice.aladin.domain.enums.ItemType;

@Data
public class GetUserItemResponse {
    private Long itemId;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private String imgName;
    private String imgUrl;
    private ItemType itemType;
    private CategoryType categoryType;

    @QueryProjection
    public GetUserItemResponse(Long itemId, String itemName, Integer price, Integer quantity, String imgName, String imgUrl, ItemType itemType, CategoryType categoryType) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.itemType = itemType;
        this.categoryType = categoryType;
    }
}
