package muyi.security.demo.security.config;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import muyi.security.demo.service.AuthorityInfoService;
import muyi.security.demo.utils.JwtUtils;
import muyi.security.demo.utils.RedisUtils;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

   private final RedisUtils redisUtils;
   private final  JwtUtils jwtUtils;

   private final AuthorityInfoService authorityInfoService;
   public TokenConfig(RedisUtils redisUtils,JwtUtils jwtUtils,AuthorityInfoService authorityInfoService){
       this.redisUtils=redisUtils;
       this.jwtUtils=jwtUtils;
       this.authorityInfoService=authorityInfoService;
   }
    @Override
    public void configure(HttpSecurity http)  {
        TokenFilter tokenFilter=new TokenFilter(redisUtils,jwtUtils,authorityInfoService);
        http.addFilterBefore(tokenFilter, AnonymousAuthenticationFilter.class);
    }
}
