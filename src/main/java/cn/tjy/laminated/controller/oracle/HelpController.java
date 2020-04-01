package cn.tjy.laminated.controller.oracle;

import cn.tjy.laminated.service.mysql.books.BooksService;
import cn.tjy.laminated.service.oracle.help.HelpService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
//@RequestMapping(value = "/Help")
public class HelpController {

    @Resource
    private HelpService helpService;//获取资源
    @Resource
    private BooksService booksService;//获取资源

}
