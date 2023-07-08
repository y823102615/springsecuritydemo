package muyi.security.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import muyi.security.demo.entity.StudentInfo;
import muyi.security.demo.mapper.StudentInfoMapper;
import muyi.security.demo.service.StudentInfoService;
import org.springframework.stereotype.Service;

@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements StudentInfoService {
}
