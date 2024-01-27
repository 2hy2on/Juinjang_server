package umc.th.juinjang.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import umc.th.juinjang.apiPayload.ApiResponse;
import umc.th.juinjang.apiPayload.code.status.SuccessStatus;
import umc.th.juinjang.converter.limjang.LimjangPostConverter;
import umc.th.juinjang.model.dto.image.ImageUploadRequestDTO;
import umc.th.juinjang.model.dto.image.ImageUploadRequestDTO.ImageDto;
import umc.th.juinjang.model.dto.image.ImageUploadResponseDTO;
import umc.th.juinjang.model.dto.limjang.LimjangPostRequestDTO;
import umc.th.juinjang.model.dto.limjang.LimjangPostResponseDTO;
import umc.th.juinjang.model.dto.limjang.LimjangPostResponseDTO.PostDTO;
import umc.th.juinjang.service.ImageService.ImageCommandService;
import umc.th.juinjang.service.LimjangService.LimjangCommandService;
import umc.th.juinjang.service.LimjangService.LimjangQueryService;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
@Validated
public class ImageController {

  private final LimjangCommandService limjangCommandService;

  private final ImageCommandService imageCommandService;

   // Long 타입을 리턴하고 싶은 경우 붙여야 함 (Long - 객체)
   @CrossOrigin
   @Operation(summary = "사진 생성 API", description = "사진 업로드 api입니다.")
   @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
       produces = MediaType.APPLICATION_JSON_VALUE)
  public ApiResponse uploadImages(
   @RequestParam(name = "limjangId") Long limjangId, @RequestPart(name = "images") List<MultipartFile> images)
    {
      imageCommandService.uploadImages(limjangId ,images);
     return ApiResponse.of(SuccessStatus.IMAGE_UPDATE, null);
  }
}
