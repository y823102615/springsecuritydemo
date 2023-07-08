package muyi.security.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import muyi.security.demo.entity.AuthorityInfo;
import muyi.security.demo.mapper.AuthorityInfoMapper;
import muyi.security.demo.service.AuthorityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityInfoServiceImpl extends ServiceImpl<AuthorityInfoMapper, AuthorityInfo>implements AuthorityInfoService {

    private final AuthorityInfoMapper authorityInfoMapper;


    @Override
    public List<AuthorityInfo> getAuthorityByName(String name) {
        List<AuthorityInfo> authorityInfoList=new ArrayList<>();
        QueryWrapper<AuthorityInfo>queryWrapper=new QueryWrapper<>();
        if(StrUtil.isNotBlank(name)){
            queryWrapper.eq("username",name);
            authorityInfoList=authorityInfoMapper.selectList(queryWrapper);
        }

        return authorityInfoList;
    }
}
