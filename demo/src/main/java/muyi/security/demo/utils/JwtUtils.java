package muyi.security.demo.utils;

import cn.hutool.core.lang.generator.UUIDGenerator;
import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    String key="muyi666666666666666666666666666666666666666";

    private  JwtBuilder  jwtBuilder;
    private  JwtParser jwtParser;
    public JwtUtils(){
        jwtBuilder=Jwts.builder().signWith(SignatureAlgorithm.HS256, key);
        jwtParser=Jwts.parserBuilder().setSigningKey(key).build();
    }
    public Object parse(String token){
       return jwtParser.parse(token).getBody();
    }
    public String generateToken(Authentication authentication){
        return jwtBuilder.setId(IdUtil.simpleUUID())
                .claim("user",authentication.getName())
                .setSubject(authentication.getName())
                .compact();
    }

}
