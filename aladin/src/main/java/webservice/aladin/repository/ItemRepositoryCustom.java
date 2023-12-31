package webservice.aladin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webservice.aladin.controller.api.dto.sort.ItemSearch;
import webservice.aladin.domain.dto.item.GetPreViewItemResponse;
import webservice.aladin.domain.dto.item.GetUserItemResponse;

import java.util.List;

public interface ItemRepositoryCustom {
    // 상품 이름으로 상품 조회
    Page<GetPreViewItemResponse> searchByItemName(ItemSearch itemSearch, Pageable pageable);

    // 상품 카테고리별 상품 조회
    Page<GetPreViewItemResponse> sortByCategoryType(String code, Pageable pageable);

    // 상품 이름 검색 기능 + 카테고리별 정렬
    Page<GetPreViewItemResponse> searchByItemNameAndCategoryType(ItemSearch itemSearch, String code, Pageable pageable);

    // 상품 낮은 가격별 정렬
    Page<GetPreViewItemResponse> sortByItemPriceASC(Pageable pageable);

    // 상품 높은 가격별 정렬
    Page<GetPreViewItemResponse> sortByItemPriceDESC(Pageable pageable);

    // 회원별 등록한 상품 조회
    List<GetUserItemResponse> sortByUser(String uploaderId);

}
