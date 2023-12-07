package webservice.aladin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webservice.aladin.domain.entity.ItemImg;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
    List<ItemImg> findAllByItem_id(Long id);
    ItemImg findByImgName(String imgName);
}
