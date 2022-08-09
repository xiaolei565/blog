package cn.ustc.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
// 配置文件的前缀
@ConfigurationProperties(prefix = "xiaolei565.jwt")
public class JwtUtil {

    /** 过期时间*/
    private long expire;
    /** 密钥*/
    private String secret;
    private String header;

    /**
     * 生成jwt
     * @param username
     * @return
     */
    public String generateToken(String username) {

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);

        return Jwts.builder()
                // 设置头参数
                .setHeaderParam("typ", "JWT")
                // 目标主体
                .setSubject(username)
                // 创建时间
                .setIssuedAt(nowDate)
                // 过期时间
                .setExpiration(expireDate)
                // 加密算法和密钥
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /** 解析jwt
     *
     * @param jwt
     * @return
     */
    public Claims getClaimByToken(String jwt) {
        try {
            return Jwts.parser()
                    // 密钥
                    .setSigningKey(secret)
                    // 解析
                    .parseClaimsJws(jwt)
                    // 获得body
                    .getBody();
        } catch (Exception e) {
            // 只要出现异常就返回null即可
            return null;
        }
    }

    /**jwt是否过期
     *
     */

    public boolean isTokenExpired(Claims claims) {
        // 如果过期时间在当前时间之前，那就代表过期了，否则就是没过期
        return claims.getExpiration().before(new Date());
    }

}
