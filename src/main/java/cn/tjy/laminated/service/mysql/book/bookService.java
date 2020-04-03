package cn.tjy.laminated.service.mysql.book;

import cn.tjy.laminated.pojo.mysql.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface bookService {

    Boolean getBoolean(String book) throws Exception;

    List<Book> getBook();

    int addBook(Book books);
}
