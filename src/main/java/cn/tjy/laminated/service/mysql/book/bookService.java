package cn.tjy.laminated.service.mysql.book;

import org.apache.ibatis.annotations.Param;

public interface bookService {

    Boolean getBoolean(String book) throws Exception;
}
