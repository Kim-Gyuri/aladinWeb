package webservice.aladin.domain.dto.item;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * webservice.aladin.domain.dto.item.QGetPreViewItemResponse is a Querydsl Projection type for GetPreViewItemResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGetPreViewItemResponse extends ConstructorExpression<GetPreViewItemResponse> {

    private static final long serialVersionUID = 2143783606L;

    public QGetPreViewItemResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<String> imgName, com.querydsl.core.types.Expression<String> imgUrl, com.querydsl.core.types.Expression<webservice.aladin.domain.enums.ItemType> itemType, com.querydsl.core.types.Expression<webservice.aladin.domain.enums.CategoryType> categoryType) {
        super(GetPreViewItemResponse.class, new Class<?>[]{long.class, String.class, int.class, int.class, String.class, String.class, webservice.aladin.domain.enums.ItemType.class, webservice.aladin.domain.enums.CategoryType.class}, id, itemName, price, quantity, imgName, imgUrl, itemType, categoryType);
    }

}

