package cn.tjy.laminated.service.mysql.books;

import cn.tjy.laminated.dao.mysql.books.BooksMapper;
import cn.tjy.laminated.pojo.mysql.Books;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BooksServiceimpl implements BooksService {

    @Resource
    private BooksMapper booksMapper;

    @Override
    public List<Books> getBooksList(String b_book, String b_lot) throws Exception {
        return booksMapper.getBooksList(b_book,b_lot);
    }

    @Override
    public Books getBooks(String b_book,String b_lot,Integer b_state) throws Exception {
        return booksMapper.getBooks(b_book,b_lot,b_state);
    }

    @Override
    public int addBooks(Books books) {
        return booksMapper.addBooks(books);
    }

    @Override
    public int updateBooks(Books books) throws Exception {
        return booksMapper.updateBooks(books);
    }

    @Override
    public int delBooks(Books books) throws Exception {
        return booksMapper.delBooks(books);
    }



}
