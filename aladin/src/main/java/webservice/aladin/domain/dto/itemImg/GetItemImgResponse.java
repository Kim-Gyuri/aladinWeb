package webservice.aladin.domain.dto.itemImg;

import lombok.Data;
import org.modelmapper.ModelMapper;
import webservice.aladin.domain.entity.ItemImg;
import webservice.aladin.domain.enums.IsMainImg;

@Data
public class GetItemImgResponse {
    private Long id;
    private String imgName;
    private String originImgName;
    private String savePath;
    private IsMainImg YN;
    private static ModelMapper modelMapper = new ModelMapper();
    public static GetItemImgResponse of(ItemImg itemImg) {
        return modelMapper.map(itemImg, GetItemImgResponse.class);
    }
}
