package webservice.aladin.domain.dto.itemImg;

import lombok.Data;
import webservice.aladin.domain.entity.Item;
import webservice.aladin.domain.enums.IsMainImg;

@Data
public class CreateImgRequest {
    private IsMainImg YN;
    private Item item;

    public CreateImgRequest(Item itemEntity) {
        YN = IsMainImg.N;
        item = itemEntity;
    }


}