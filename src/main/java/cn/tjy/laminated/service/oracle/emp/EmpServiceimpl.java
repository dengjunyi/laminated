package cn.tjy.laminated.service.oracle.emp;

import cn.tjy.laminated.dao.oracle.emp.EmpMapper;
import cn.tjy.laminated.pojo.oracle.Emp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmpServiceimpl implements EmpService {

    @Resource
    private EmpMapper empMapper;

    @Override
    public void count(Emp emp) {
        System.out.println("666");
        empMapper.count(emp);
    }
}
