package cn.tjy.laminated.service.oracle.users;

import cn.tjy.laminated.dao.oracle.users.UsersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UsersServiceimpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;


    @Override
    public void getUsers(Map<String, Object> map) {
        usersMapper.getUsers(map);
    }

    @Override
    public void login(Map<String, Object> map) {
        usersMapper.login(map);
    }

    @Override
    public void lot(Map<String, Object> map) {
        usersMapper.lot(map);
    }
}
