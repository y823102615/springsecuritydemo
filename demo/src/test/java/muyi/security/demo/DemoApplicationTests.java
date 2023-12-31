package muyi.security.demo;

import lombok.RequiredArgsConstructor;
import muyi.security.demo.entity.AuthorityInfo;
import muyi.security.demo.entity.StudentInfo;
import muyi.security.demo.mapper.LoginInfoMapper;
import muyi.security.demo.security.dto.UserDto;
import muyi.security.demo.service.AuthorityInfoService;
import muyi.security.demo.service.LoginInfoService;
import muyi.security.demo.service.StudentInfoService;
import muyi.security.demo.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@MapperScan("muyi.security.demo.mapper")
@SpringBootTest
class DemoApplicationTests {



   //private final StudentInfoService studentInfoService;
   // private final AuthorityInfoService authorityInfoService;
    @Autowired
    private   RedisUtils redisUtils;
   // private final LoginInfoMapper loginInfoMapper;
    @Test
    void contextLoads() {
//        BCryptPasswordEncoder bcp=new BCryptPasswordEncoder();
//        System.out.println("加密前：");
//
//        String pswd="111";
//        System.out.println(pswd);
//        System.out.println("加密后");
//        System.out.println(bcp.encode(pswd));
//        List<StudentInfo> studentInfoList= studentInfoService.list(null);
//
//
//        System.out.println(studentInfoList.size());
//        List<AuthorityInfo>authorityInfoServiceList= authorityInfoService.getAuthorityByName("张三");
//        System.out.println(authorityInfoServiceList.size());

          System.out.println(((UserDto)redisUtils.get("onlineuser:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiY2Y0MzYzYWMxNjQ0ODI3ODUxNjJhNDQ0YTc4MWU3OCIsInVzZXIiOiJ1c2VyIiwic3ViIjoidXNlciJ9.Rp8Bz3ByhDPfZMA08yzJNMslqv12HkVeF_DYU19ZVEE")).getUsername());
    }

}
