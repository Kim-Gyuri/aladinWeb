package webservice.aladin.domain.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import webservice.aladin.domain.dto.itemImg.GetItemImgResponse;
import webservice.aladin.domain.enums.CategoryType;
import webservice.aladin.domain.enums.ItemSellStatus;
import webservice.aladin.domain.enums.ItemType;

import java.util.List;

@Data
public class GetItemResponse {
    private Long id;
    @NotBlank
    private String itemName;
    @NotNull
    private Integer price;
    @NotNull
    private Integer quantity;
    private ItemType itemType;
    private CategoryType categoryType;
    private ItemSellStatus status;
    private List<GetItemImgResponse> itemImgDtoList;

}
