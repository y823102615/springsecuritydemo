package muyi.security.demo.security.config;


import cn.hutool.core.util.StrUtil;
import com.sun.net.httpserver.HttpExchange;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muyi.security.demo.security.dto.UserDto;
import muyi.security.demo.utils.JwtUtils;
import muyi.security.demo.utils.RedisUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@AllArgsConstructor
public class TokenFilter implements Filter {
    private RedisUtils redisUtils;
    private JwtUtils jwtUtils;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        //从请求当中获取token信息
        String token=getTokenFromRequest(httpServletRequest);
        UserDto userDto=null;
        if(StrUtil.isNotBlank(token)){
            log.info("login in token is:"+token);
            //从redis当中查询token信息
            userDto=(UserDto) redisUtils.get("onlineuser:"+token);
        }
        if(userDto!=null){
            //若从reids当中获取到用户则为登录了的用户这里对认证过的用户存储到SecurityContext当中
            Claims claim= (Claims) jwtUtils.parse(token);
            User principal=new User(claim.getSubject(),"***",new ArrayList<>());
            Authentication authentication=new UsernamePasswordAuthenticationToken(principal,token,new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(servletRequest,servletResponse);
    }

    public String getTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer")) {
            // 去掉令牌前缀
            return bearerToken.replace("Bearer", "");
        } else {
            //log.debug("非法Token：{}", bearerToken);

        }

        return null;
    }
}
