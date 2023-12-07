package webservice.aladin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webservice.aladin.domain.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long>, SalesRepositoryCustom {

}

