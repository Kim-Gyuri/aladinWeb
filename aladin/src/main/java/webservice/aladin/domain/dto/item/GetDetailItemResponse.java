package webservice.aladin.domain.dto.item;

import lombok.Data;
import webservice.aladin.domain.dto.itemImg.GetItemImgResponse;
import webservice.aladin.domain.entity.Item;
import webservice.aladin.domain.enums.CategoryType;
import webservice.aladin.domain.enums.ItemSellStatus;
import webservice.aladin.domain.enums.ItemType;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class GetDetailItemResponse {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private ItemType itemType;
    private CategoryType categoryType;
    private ItemSellStatus status;

    private Integer count;
    private List<GetItemImgResponse> itemImgDtoList;

    public GetDetailItemResponse(Item item) {
        this.id = item.getId();
        this.itemName = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getStockQuantity();
        this.itemType = item.getItemType();
        this.categoryType = item.getCategoryType();
        this.status = item.getStatus();
        this.itemImgDtoList = getImgDtoList(item);
    }

    private List<GetItemImgResponse> getImgDtoList(Item item) {
        return item.getImgList().stream()
                .map(itemImg -> GetItemImgResponse.of(itemImg))
                .collect(toList());
    }

}
