package muyi.security.demo.controller;

import lombok.RequiredArgsConstructor;
import muyi.security.demo.entity.StudentInfo;
import muyi.security.demo.service.StudentInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
public class student {
    private final StudentInfoService studentInfoService;



    @RequestMapping("/hello")
    //@PreAuthorize("@authorityCheck.check('hello')")
    @PreAuthorize("hasAuthority('hello')")
    public List<StudentInfo> hello(){
        List<StudentInfo>studentInfoList=null;
        studentInfoList=studentInfoService.list(null);
        return studentInfoList;
    }
}
