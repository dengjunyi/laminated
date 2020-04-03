package cn.tjy.laminated.service.mysql.book;

import cn.tjy.laminated.dao.mysql.book.bookMapper;
import cn.tjy.laminated.dao.mysql.books.BooksMapper;
import cn.tjy.laminated.pojo.mysql.Book;
import cn.tjy.laminated.pojo.mysql.Books;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class bookServiceimpl implements bookService
{

    @Resource
    bookMapper bookMapper;

    @Override
    public Boolean getBoolean(String book) throws Exception {
        Boolean iaBaen=false;
        Book books = bookMapper.getBoolean(book);
        if (books!=null){
            iaBaen=true;
        }
        System.out.println("iaBaen:"+iaBaen);
        return iaBaen;
    }

    @Override
    public List<Book> getBook() {
        return bookMapper.getBook();
    }

    @Override
    public int addBook(Book books) {
        return bookMapper.addBook(books);
    }
}
