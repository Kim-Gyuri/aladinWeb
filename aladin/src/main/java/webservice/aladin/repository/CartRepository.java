package webservice.aladin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import webservice.aladin.domain.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
