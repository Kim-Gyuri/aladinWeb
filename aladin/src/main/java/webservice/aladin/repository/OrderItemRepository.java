package webservice.aladin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webservice.aladin.domain.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, OrderItemRepositoryCustom {
}
