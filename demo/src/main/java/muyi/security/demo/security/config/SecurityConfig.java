package muyi.security.demo.security.config;

import lombok.RequiredArgsConstructor;
import muyi.security.demo.security.JwtAuthenticationEntryPoint;
import muyi.security.demo.service.AuthorityInfoService;
import muyi.security.demo.utils.JwtUtils;
import muyi.security.demo.utils.RedisUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig  {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final  JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final  RedisUtils redisUtils;
    private  final JwtUtils jwtUtils;
    private  final AuthorityInfoService authorityInfoService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        return http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                //防止iframe 跨域
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                //不创建会话
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(securityConfigurerAdapter())
                .and()
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    private TokenConfig  securityConfigurerAdapter(){
        return new TokenConfig(redisUtils,jwtUtils,authorityInfoService);
    }


}
