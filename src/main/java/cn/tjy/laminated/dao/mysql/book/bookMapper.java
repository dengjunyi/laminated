package cn.tjy.laminated.dao.mysql.book;


import cn.tjy.laminated.pojo.mysql.Book;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.EAN;

import java.io.Serializable;
import java.util.List;

public interface bookMapper{

    Book getBoolean(@Param("book") String book) throws Exception;


    List<Book> getBook();
    int addBook(Book books);
}
