package muyi.security.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import muyi.security.demo.entity.AuthorityInfo;

import java.util.List;

public interface AuthorityInfoService extends IService<AuthorityInfo> {
    public abstract List<AuthorityInfo> getAuthorityByName(String name);
}
