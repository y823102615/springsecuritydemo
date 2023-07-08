package muyi.security.demo.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muyi.security.demo.entity.AuthorityInfo;
import muyi.security.demo.entity.LoginInfo;
import muyi.security.demo.security.dto.Authoritydto;
import muyi.security.demo.service.AuthorityInfoService;
import muyi.security.demo.service.LoginInfoService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginInfoService loginInfoService;
    private final AuthorityInfoService authorityInfoService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=null;
        //从数据库中获取账号密码信息
        LoginInfo loginInfo=loginInfoService.getLoginInfoByUsername(username);

        if(loginInfo==null){
            //用户未找到
            throw new  UsernameNotFoundException("用户未找到");
        }
        else {
            List<AuthorityInfo>authorityInfoList;
            //获取用户对应的权限信息
            authorityInfoList=authorityInfoService.getAuthorityByName(loginInfo.getUsername());
            //将查询到的账号密码封装成UserDetails返回
            user=new User(loginInfo.getUsername(),loginInfo.getPassword(),authorityInfoList.stream()
                            .map(AuthorityInfo::getAuthority)
                            .distinct()
                            .map(Authoritydto::new).collect(Collectors.toList()));
        }
        log.info("from UserDetails get Logininfo");
        return user;
    }
}
