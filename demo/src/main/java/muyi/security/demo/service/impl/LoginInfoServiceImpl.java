package muyi.security.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import muyi.security.demo.entity.LoginInfo;
import muyi.security.demo.mapper.LoginInfoMapper;
import muyi.security.demo.service.LoginInfoService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo>implements LoginInfoService {
    private LoginInfoMapper loginInfoMapper;
    @Override
    public LoginInfo getLoginInfoByUsername(String username) {
        LoginInfo loginInfo=null;
        QueryWrapper<LoginInfo>queryWrapper=new QueryWrapper<>();
        if(StrUtil.isNotBlank(username)){
            queryWrapper.eq("username",username);
            loginInfo=loginInfoMapper.selectOne(queryWrapper);
        }

        return loginInfo;
    }
}
