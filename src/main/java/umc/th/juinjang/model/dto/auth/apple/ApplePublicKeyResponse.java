package umc.th.juinjang.model.dto.auth.apple;

import lombok.Getter;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Getter
public class ApplePublicKeyResponse {
    private List<Key> keys;

    @Getter
    public static class Key {
        private String kty;
        private String kid;
        private String use;
        private String alg;
        private String n;
        private String e;
    }

    //받은 public key 중 kid alg 같은거 찾기
    // Identity Token 헤더에 있는것과비교
    public Key getMatchedKeyBy(String kid, String alg) throws AuthenticationException {
        return keys.stream()
                .filter(key -> key.getKid().equals(kid) && key.getAlg().equals(alg))
                .findAny()
                .orElseThrow(AuthenticationException::new);
    }

}
