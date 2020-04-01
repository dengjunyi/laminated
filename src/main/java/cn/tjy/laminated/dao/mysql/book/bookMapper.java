package cn.tjy.laminated.dao.mysql.book;


import cn.tjy.laminated.pojo.mysql.Book;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

public interface bookMapper{

    Book getBoolean(@Param("book") String book) throws Exception;
}
