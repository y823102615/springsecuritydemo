package muyi.security.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import muyi.security.demo.entity.LoginInfo;

public interface LoginInfoService extends IService<LoginInfo> {


    public abstract LoginInfo getLoginInfoByUsername(String username);
}
