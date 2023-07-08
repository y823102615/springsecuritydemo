package muyi.security.demo.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muyi.security.demo.security.dto.UserDto;
import muyi.security.demo.utils.JwtUtils;
import muyi.security.demo.utils.RedisUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private JwtUtils jwtUtils;
    private RedisUtils redisUtils;
    @RequestMapping("/login")
    public String hello(@Validated @RequestBody UserDto userDto){

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword());
        Authentication authentication=authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        //将认证信息存储SecurityContext当中
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //根据Authentication生成token
        String token=jwtUtils.generateToken(authentication);
        User user=(User) authentication.getPrincipal();
        //将token存入redis来验证客户端的二次访问
        redisUtils.set("onlineuser:"+token,new UserDto(userDto.getUsername(),"***"));
        log.info("hello Controller generate token:"+token);

        return token;

    }
}
