package umc.th.juinjang.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KakaoUnlinkClient", url = "https://kapi.kakao.com")
public interface KakaoUnlinkClient {

    @PostMapping(value = "/v1/user/unlink", consumes = "application/x-www-form-urlencoded")
    ResponseEntity<String> unlinkUser(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(name = "target_id_type", defaultValue = "user_id") String targetIdType,
            @RequestParam("target_id") Long targetId
    );
}