package webservice.aladin.controller.api.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import webservice.aladin.domain.dto.itemImg.GetItemImgResponse;
import webservice.aladin.domain.entity.Item;
import webservice.aladin.domain.entity.ItemImg;
import webservice.aladin.domain.enums.CategoryType;
import webservice.aladin.domain.enums.ItemSellStatus;
import webservice.aladin.domain.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class GetItemResponse {
    private Long id;
    private String name;
    private int price;
    private int quantity;
    private ItemType itemType;
    private CategoryType categoryType;
    private ItemSellStatus status;
    private List<GetItemImgResponse> itemImgDtoList;

    public GetItemResponse(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getStockQuantity();
        this.itemType = item.getItemType();
        this.categoryType = item.getCategoryType();
        this.status = item.getStatus();
        getImgDtoList(item);
    }

    private void getImgDtoList(Item item) {
        itemImgDtoList = new ArrayList<>();
        for (ItemImg itemImg : item.getImgList()) {
            this.itemImgDtoList.add(GetItemImgResponse.of(itemImg));
        }
    }


}