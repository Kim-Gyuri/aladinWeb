package webservice.aladin.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import webservice.aladin.domain.dto.cart.GetCartResponse;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Serializable {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String email;

    @Embedded
    private Address address;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "sales_id")
    private Sales sales;

    // H2 DB 테스트 할 때, 입력 데이터를 만들기 위해
    @Builder(builderMethodName = "userBuilder")
    public User(String loginId, String password, String name, String email, Address address) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.cart = new Cart();
        this.sales = new Sales();
    }

    public void createSales(Sales sales) {
        this.sales = sales;
    }

    public void uploadItem(Item item) {
        item.sellerInfo(loginId); // 상품에 판매자 정보를 등록한다.
        sales.uploadItem(item);
    }

    public void createCart(Cart cart) {
        this.cart = cart;
    }

    public void addCartItem(OrderItem orderItem) {
        cart.addOrderItem(orderItem);
        sales.takeOrder(orderItem);
    }

    public List<GetCartResponse> getWishList() {
        return cart.getOrderItemList()
                .stream()
                .map(OrderItem::toWishItemDto)
                .collect(Collectors.toList());
    }

    public boolean checkOrderItemDuplicate(OrderItem orderItem) {
        return cart.getOrderItemList()
                .stream()
                .map(OrderItem::getItem)
                .anyMatch(v -> v.getId() == orderItem.getItemId());
    }

    public Sales searchSales() {
        return this.sales;
    }

    public int income() {
        Sales sales = searchSales();
        return sales.getTotalRevenue();
    }

    @Override
    public String toString() {
        return "User Info {" + "id=" + id + ", name=" + name + ", loginId=" + loginId + ", password =" + password + ", address =" + address + '}';
    }

}
