package umc.th.juinjang.service.ImageService;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.th.juinjang.apiPayload.code.status.ErrorStatus;
import umc.th.juinjang.apiPayload.exception.handler.LimjangHandler;
import umc.th.juinjang.apiPayload.exception.handler.MemberHandler;
import umc.th.juinjang.converter.image.ImageListConverter;
import umc.th.juinjang.converter.limjang.LimjangDetailConverter;
import umc.th.juinjang.converter.limjang.LimjangMainListConverter;
import umc.th.juinjang.converter.limjang.LimjangTotalListConverter;
import umc.th.juinjang.model.dto.image.ImageListResponseDTO;
import umc.th.juinjang.model.dto.limjang.LimjangDetailResponseDTO.DetailDto;
import umc.th.juinjang.model.dto.limjang.LimjangMainViewListResponsetDTO;
import umc.th.juinjang.model.dto.limjang.LimjangTotalListResponseDTO;
import umc.th.juinjang.model.entity.Image;
import umc.th.juinjang.model.entity.Limjang;
import umc.th.juinjang.model.entity.Member;
import umc.th.juinjang.repository.image.ImageRepository;
import umc.th.juinjang.repository.limjang.LimjangPriceRepository;
import umc.th.juinjang.repository.limjang.LimjangRepository;
import umc.th.juinjang.repository.limjang.MemberRepository;
import umc.th.juinjang.repository.limjang.ScrapRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageQueryServiceImpl implements ImageQueryService {

  private final ImageRepository imageRepository;
  private final LimjangRepository limjangRepository;

  @Override
  @Transactional(readOnly = true)
  public ImageListResponseDTO.ImagesListDTO getImageList(Long limjangId) {
    Limjang findLimjang = limjangRepository.findById(limjangId)
        .orElseThrow(() -> new LimjangHandler(ErrorStatus.LIMJANG_NOTFOUND_ERROR));

    List<Image> imageList = imageRepository.findImagesByLimjangId(findLimjang);

    return ImageListConverter.toImageListDto(imageList);

  }
}
