package cn.tjy.laminated.service.oracle.users;

import cn.tjy.laminated.pojo.oracle.Users;

import java.util.Map;


public interface UsersService {
    public void getUsers(Map<String,Object> map);

    public void login(Map<String,Object> map);

    public void lot(Map<String,Object> map);
}
