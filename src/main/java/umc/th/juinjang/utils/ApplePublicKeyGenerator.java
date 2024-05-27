package umc.th.juinjang.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.th.juinjang.apiPayload.ExceptionHandler;
import umc.th.juinjang.apiPayload.code.status.ErrorStatus;
import umc.th.juinjang.model.dto.auth.apple.ApplePublicKeyResponse;

import javax.naming.AuthenticationException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ApplePublicKeyGenerator {

    public PublicKey generatePublicKey(Map<String,String> tokenHeaders, ApplePublicKeyResponse applePublicKeys) {
        ApplePublicKeyResponse.Key publicKey = applePublicKeys.getMatchedKeyBy(tokenHeaders.get("kid"),tokenHeaders.get("alg"));
        return getPublicKey(publicKey);
    }

    private PublicKey getPublicKey(ApplePublicKeyResponse.Key publicKey) {

        byte[] nBytes = Base64.getUrlDecoder().decode(publicKey.getN());
        byte[] eBytes = Base64.getUrlDecoder().decode(publicKey.getE());

        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(1, nBytes),
                new BigInteger(1, eBytes));

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(publicKey.getKty());
            return keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ExceptionHandler(ErrorStatus.PUBLICKEY_ERROR_IN_APPLE_LOGIN);
        }
    }
}
