package cn.tjy.laminated.service.mysql.books;

import cn.tjy.laminated.pojo.mysql.Books;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

public interface BooksService {

    /**
     * 查询所有
     *
     * @param b_book
     * @param b_lot
     * @return
     * @throws Exception
     */
    public List<Books> getBooksList(String b_book, String b_lot) throws Exception;

    /**
     * 查询第一个录入book板的配方
     *
     * @param b_book
     * @return
     * @throws Exception
     */
    public Books getBooks(String b_book,String b_lot,Integer b_state) throws Exception;

    /**
     * 添加
     *
     * @param books
     * @return
     */
    public int addBooks(Books books);

    /**
     * 修改
     *
     * @param books
     * @return
     * @throws Exception
     */
    public int updateBooks(Books books) throws Exception;

    /**
     * 删除
     *
     * @param books
     * @return
     */
    public int delBooks(Books books) throws Exception;


}
