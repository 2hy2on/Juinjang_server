package umc.th.juinjang.model.dto.auth.apple;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "appleClient", url = "https://appleid.apple.com/auth", configuration = FeignClient.class)
public interface AppleClient {
    @GetMapping(value = "/api/auth/apple-keys")
    ApplePublicKeyResponse getAppleAuthPublicKey();


}
