package com.thiagoaio.api.auth

import com.thiagoaio.api.auth.dto.LoginDto
import com.thiagoaio.api.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import io.jsonwebtoken.Jwts
import com.thiagoaio.api.utils.Utils
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
class AuthService {

    @Autowired
    private UserService userService

    @Value('${jwt.secret}') String jwtSecret
    @Value('${jwt.expiration}') Long jwtExpiration

    ResponseEntity<?> login(LoginDto body) {
        try {
            var user = userService.findByEmail(body.getEmail())
            if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");

            if (user.getActive() == false)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário inativo.");

            if (!Utils.compareHash(body.getPassword(), user.getPassword()))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");

            var token = generateToken(user.getId());
            return ResponseEntity.status(HttpStatus.OK).body([token: token]);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private String generateToken(UUID userId) {
        Date now = new Date()
        Date expiryDate = new Date(now.time + jwtExpiration)

        SecretKey key = getKey();

        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact()
    }

    public String validateToken(String token) {
        try {
            SecretKey key = getKey();

            def claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()

            var user = userService.findById(UUID.fromString(claims.getSubject()));
            if (user == null || user.getActive() == false) return null;

            return claims.getSubject();
        } catch (Exception e) {
            return null
        }
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
