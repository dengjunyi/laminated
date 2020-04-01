package cn.tjy.laminated.dao.mysql.books;



import cn.tjy.laminated.pojo.mysql.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface BooksMapper {

    /**
     * 查询录入正确的book板
     * @param b_book
     * @param b_lot
     * @return
     * @throws Exception
     */
    public List<Books> getBooksList(@Param("b_book") String b_book, @Param("b_lot") String b_lot) throws Exception;

    public Books getBooks(@Param("b_book")String b_book) throws Exception;

    /**
     * 添加
     * @param books
     * @return
     */
    public int addBooks(Books books);

    /**
     * 修改
     * @param books
     * @return
     * @throws Exception
     */
    public int updateBooks(Books books) throws Exception;

    /**
     * 删除
     * @param books
     * @return
     */
    public int delBooks(Books books) throws Exception;
}
