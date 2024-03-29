package Emerging.App.Backend.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTutil {

    private final String encodedSecret = EncodedSecret.getEncodedSecret();

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(encodedSecret));

    public JWTutil() {
    }

    public Jws<Claims> decodeJWT(String jwt){
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(jwt);
    }

    public String generateJWT(String username, String password){
        String jwt = Jwts.builder()
                .subject(username)
                .issuer("George's Backend")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SECRET_KEY)
                .compact();
        return jwt;
    }

}
