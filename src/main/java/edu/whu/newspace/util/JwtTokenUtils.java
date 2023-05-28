package edu.whu.newspace.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Newspace
 * @version 1.0
 * @description JWT令牌管理类
 * @date 2023/5/26 15:19
 */
@Component
public class JwtTokenUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtils.class);
    /**
     * token的过期时间
     */
    private static final long JWT_TOKEN_VALIDITY = 5*60*60*1000;

    /**
     * token的密钥
     */
    @Value("${jwt.secret}")
    private String secret;


    /**
     * 从Token中获得Claims
     * @param token     令牌
     * @return
     */
    public Claims getClaimFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成Token
     * @param userDetails   用户信息
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();//可以自由加入各种身份信息，如角色
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 对Token进行验证
     * @param token         令牌
     * @param userDetails   用户信息
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        return validateClaim(getClaimFromToken(token),userDetails);
    }

    /**
     * 对Claims进行验证
     * @param claim         验证
     * @param userDetails   用户信息
     * @return
     */
    public boolean validateClaim(Claims claim,UserDetails userDetails) {
        //可以验证其他信息，如角色
        return userDetails != null &&
                claim.getSubject().equals(userDetails.getUsername())
                && !claim.getExpiration().before(new Date());
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public Boolean isTokenExpired(String token) {
        Claims claim = getClaimFromToken(token);
        return claim.getExpiration().before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }
}
