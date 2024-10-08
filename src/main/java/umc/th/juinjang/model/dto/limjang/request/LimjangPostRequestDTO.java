package umc.th.juinjang.model.dto.limjang.request;

import jakarta.validation.constraints.*;
import java.util.List;
import lombok.Getter;
import umc.th.juinjang.validation.annotation.VaildPriceListSize;

public class LimjangPostRequestDTO {

  @Getter
  public static class PostDto {

    @NotNull
    private Integer purposeType;

    @NotNull
    private Integer propertyType;

    @NotNull
    private Integer priceType;

    @VaildPriceListSize(minSize = 1, maxSize = 2)
    private List<String> price;

    @NotBlank
    private String address;

    @NotBlank
    private String addressDetail;

    @NotBlank
    private String nickname;
  }

}
