package webservice.aladin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import webservice.aladin.domain.dto.file.CreateFileResponse;
import webservice.aladin.domain.dto.itemImg.CreateImgRequest;
import webservice.aladin.domain.entity.ItemImg;
import webservice.aladin.repository.ItemImgRepository;
import webservice.aladin.util.exception.item.NotFoundItemException;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemImgService {

    //private final FileService fileService;
    private final S3FileService s3FileService;
    private final ItemImgRepository imgRepository;


    // 상품 등록에 필요한 이미지 저장
    /*
    public Long saveItemImg(CreateImgRequest itemInfo, MultipartFile multipartFile) throws IOException {
        CreateFileResponse fileInfo = fileService.storeFile(multipartFile);

        ItemImg itemImgEntity = ItemImg.imgBuilder()
                .originImgName(fileInfo.getOriginImgName())
                .imgName(fileInfo.getImgName())
                .savePath(fileInfo.getSavePath())
                .isMainImg(itemInfo.getYN())
                .item(itemInfo.getItem())
                .build();

        ItemImg saved = imgRepository.save(itemImgEntity);
        return saved.getId();
    }
   */


    public Long saveItemImg_s3(CreateImgRequest itemInfo, MultipartFile multipartFile) throws IOException {
        CreateFileResponse fileInfo = s3FileService.upload(multipartFile, "test");

        ItemImg itemImgEntity = ItemImg.imgBuilder()
                .originImgName(fileInfo.getOriginImgName())
                .imgName(fileInfo.getImgName())
                .savePath(fileInfo.getSavePath())
                .isMainImg(itemInfo.getYN())
                .item(itemInfo.getItem())
                .build();

        ItemImg saved = imgRepository.save(itemImgEntity);
        return saved.getId();
    }

    // 이미지 이름으로 해당 이미지 객체 조회
    @Transactional(readOnly = true)
    public ItemImg findByImgName(String imgName) {
        return imgRepository.findByImgName(imgName);
    }

    @Transactional(readOnly = true)
    public ItemImg findByImgId(long id) {
        return imgRepository.findById(id)
                .orElseThrow(() -> new NotFoundItemException("해당 이미지가 없습니다."));
    }

    // 이미지 삭제
    public void delete(ItemImg imgEntity) {
        imgRepository.delete(imgEntity);
    }

}
