package webservice.aladin.controller.api.dto.sort;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import webservice.aladin.domain.enums.OrderStatus;
import webservice.aladin.domain.enums.ItemSellStatus;
@Data
public class PageDtoTestV2 {
    private final int ITEMS_PER_PAGE = 4; // 페이지당 몇개 표시할건지
    private int pageSize; // 페이지당 몇개 표시할건지
    private int startPage;
    private int endPage;
    private int curPage;
    private boolean prev, next;

    private String sortParam;
    private ItemSellStatus sellStatus;
    private OrderStatus orderStatus;
    private long total;

    @Builder
    public PageDtoTestV2(long total, Pageable pageable) {
        this.total = total;
        this.curPage = pageable.getPageNumber();
        this.pageSize = ITEMS_PER_PAGE;

        // 페이지 계산
        this.startPage = (curPage / ITEMS_PER_PAGE) * ITEMS_PER_PAGE + 1;
        this.endPage = this.startPage + ITEMS_PER_PAGE - 1;

        int realEnd = (int) (Math.ceil((total * 1.0) / pageSize));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }

    @Builder
    public PageDtoTestV2(long total, String sortParam, Pageable pageable) {
        this.total = total;
        this.sortParam = sortParam;
        this.curPage = pageable.getPageNumber();
        this.pageSize = ITEMS_PER_PAGE;

        // 페이지 계산
        this.startPage = (curPage / ITEMS_PER_PAGE) * ITEMS_PER_PAGE + 1;
        this.endPage = this.startPage + ITEMS_PER_PAGE - 1;

        int realEnd = (int) (Math.ceil((total * 1.0) / pageSize));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }

    @Builder
    public PageDtoTestV2(long total, ItemSellStatus status, Pageable pageable) {
        // 예외 처리: pageSize 값이 0 이하인 경우
        if (pageable.getPageSize() <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0.");
        }

        this.total = Math.max(total, 0); // total이 0 이하일 경우 0으로 설정
        this.sellStatus = status;
        this.curPage = pageable.getPageNumber();
        this.pageSize = Math.max(pageable.getPageSize(), 1); // pageSize가 0 이하일 경우 1로 설정

        // 페이지 계산
        if (total <= 0) {
            // total이 0 이하인 경우, 페이지 정보 초기화
            this.curPage = 1;
            this.startPage = 1;
            this.endPage = 1;
            this.prev = false;
            this.next = false;
        } else {
            this.startPage = (curPage / pageSize) * pageSize + 1;
            this.endPage = Math.min(startPage + pageSize - 1, (int) (Math.ceil((total * 1.0) / pageSize)));
            this.prev = startPage > 1;
            this.next = endPage < total;
        }
    }


    @Builder
    public PageDtoTestV2(long total, OrderStatus status, Pageable pageable) {
        this.total = total;
        this.orderStatus = status;
        this.curPage = pageable.getPageNumber();
        this.pageSize = ITEMS_PER_PAGE;

        // 페이지 계산
        this.startPage = (curPage / ITEMS_PER_PAGE) * ITEMS_PER_PAGE + 1;
        this.endPage = this.startPage + ITEMS_PER_PAGE - 1;

        int realEnd = (int) (Math.ceil((total * 1.0) / pageSize));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }

    public boolean hasPrevious() {
        return prev;
    }

    public boolean hasNext() {
        return next;
    }

    public long totalPages() {
        return total;
    }

    public int pageSize() {
        return pageSize;
    }
}
