package muyi.security.demo.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muyi.security.demo.security.dto.Authoritydto;
import muyi.security.demo.utils.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Component("authorityCheck")
@RequiredArgsConstructor
public class AuthorityCheck {
    public Boolean check(String authority){
        log.info("正在请求 访问接口："+authority+"的权限");
        List<String> authoritydtoList;
        //从SecurityContext当中获取用户账号调用loadUserByname获取当前用户的权限信息
        String username=((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        UserDetailsService userDetailsService= SpringUtils.getBean(UserDetailsService.class);
        authoritydtoList=userDetailsService.loadUserByUsername(username).getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return authoritydtoList.contains(authority);


    }
}
