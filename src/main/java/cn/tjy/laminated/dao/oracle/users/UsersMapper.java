package cn.tjy.laminated.dao.oracle.users;

import java.util.Map;

public interface UsersMapper {

  public void getUsers(Map<String,Object> map);

  public void login(Map<String,Object> map);

  public void lot(Map<String,Object> map);
}
