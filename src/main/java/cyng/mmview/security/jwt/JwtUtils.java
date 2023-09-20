package cyng.mmview.security.jwt;

import cyng.mmview.security.auth.MembersDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;


    @PostConstruct
    protected void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //만료시간 1시간 설정
    private final long expTime = 1000L * 60 * 60;
    private final MembersDetailsService membersDetailsService;


    //유저 아이디로 토큰 생성
    public String createToken(String accntId) {
        Claims claims = Jwts.claims().setSubject(accntId);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    //유저 아이디 획득
    public String getAccount(String token) {
        System.out.println("===============JwtUtils.getAccount=================");
        return Jwts.parserBuilder().setSigningKey(key)
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    //권한 정보 획득
    public Authentication getAuthentication(String token) {
        System.out.println("=================JwtUtils.getAuthentication=================");
        UserDetails membersDetails = membersDetailsService.loadUserByUsername(this.getAccount(token));
        return new UsernamePasswordAuthenticationToken(membersDetails.getUsername(), membersDetails.getPassword());
    }

    //Authorization Header 를 통해 인증..
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    //토큰 검증
    public boolean validateToken(String token) {
        try {
            //Bearer 검증
            if (!token.substring(0, "Bearer ".length()).equalsIgnoreCase("Bearer ")) {
                return false;
            } else {
                token = token.split(" ")[1].trim();
            }
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date()); //현재시간보다 이전에 만료된 경우 false, 만료되지 않은 경우 true
        } catch (Exception e) {
            return false;
        }
    }
}
