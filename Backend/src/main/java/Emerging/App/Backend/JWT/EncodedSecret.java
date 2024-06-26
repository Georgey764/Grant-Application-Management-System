package Emerging.App.Backend.JWT;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class EncodedSecret {
    private static String secretString = System.getenv("SECRET_KEY");
    private static byte[] secretByte = secretString.getBytes(StandardCharsets.UTF_8);

    private static SecretKey secretKey = Keys.hmacShaKeyFor(secretByte);

    private static String ENCODED_SECRET = Encoders.BASE64.encode(secretKey.getEncoded());

    public static String getEncodedSecret(){
        return ENCODED_SECRET;
    }

}
