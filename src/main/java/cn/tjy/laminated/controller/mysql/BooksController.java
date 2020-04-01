package cn.tjy.laminated.controller.mysql;

import cn.tjy.laminated.pojo.mysql.Books;
import cn.tjy.laminated.pojo.mysql.Smelter;
import cn.tjy.laminated.pojo.oracle.Users;
import cn.tjy.laminated.service.mysql.book.bookService;
import cn.tjy.laminated.service.mysql.books.BooksService;
import cn.tjy.laminated.service.mysql.smelter.SmelterService;
import cn.tjy.laminated.service.oracle.help.HelpService;
import cn.tjy.laminated.service.oracle.users.UsersService;

import cn.tjy.laminated.util.sound;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping(value = "/emp")
//@EnableAutoConfiguration//自动加载配置信息
public class BooksController {


    @Resource
    private cn.tjy.laminated.service.mysql.book.bookService bookService;//获取资源
    @Resource
    private BooksService booksService;//获取资源
    @Resource
    private HelpService helpService;//获取资源
    @Resource
    private UsersService usersService;//获取资源
    @Resource
    private SmelterService smelterService;//获取资源


    //跳转主界面
    @RequestMapping("/index")
    public String idnexs() {
        return "index";
    }

    //跳转功能列表  fun
    @RequestMapping("/fun")
    public String fun() {


        Map map = new HashMap();
        map.put("zip_code", "");
        map.put("fitip", "DMC");
        map.put("in_ip", "192.168.1.102");
        map.put("IN_TYPE", 1);
    /*    map.put("EMPLOYEE_NAME", "12");
        map.put("KEY73", 6);
        map.put("HINT", "12");*/
       /* Users users = usersService.getUsers(null, "DMC", "192.168.1.102", "0.00F",
                "12", 6, "12");*/
        System.out.println("6666");
        usersService.getUsers(map);
        System.out.println("调用成功");
        System.out.println("lot号:" + map.get("KEY73"));
        System.out.println("EMPLOYEE_NAME:" + map.get("EMPLOYEE_NAME"));
        System.out.println("HINT:" + map.get("HINT"));
        return "from/fun";
    }

    @RequestMapping("/info")
    public String info(@RequestParam(name = "cmd") String cmd, @RequestParam(name = "status") Integer status) {
        System.out.println("cmd:" + cmd);
        System.out.println("status:" + status);
        System.out.println("进入信息比对界面");
        return "from/info";
    }

    //信息比对  info
    @RequestMapping(value = "/Boolean", method = RequestMethod.POST)
    @ResponseBody
    public Boolean isTrue(@RequestParam("book") String book) throws Exception {
        Boolean aBoolean = bookService.getBoolean(book);
        System.out.println("aBoolean"+aBoolean);
        return aBoolean;
    }

    //信息比对  info
    @RequestMapping(value = "/sound", method = RequestMethod.POST)
    @ResponseBody
    public void sound(@RequestParam("name") String name) throws Exception {
        System.out.println("book:"+name);
        sound.show(name);
    }


    @RequestMapping("/get")
    @ResponseBody
    public String getBooksList(HttpSession session) throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        System.out.println("格式化输出：" + sdf.format(date));
        List<Books> booksList = booksService.getBooksList("888", null);
        int j = 0;
        for (Books books : booksList) {
            j = books.getB_quantity() + j;
        }
        String b_model = booksList.get(0).getB_model();
        String b_formula = booksList.get(0).getB_formula();
        System.out.println("类型:" + b_model);
        System.out.println("配方:" + b_formula);
        System.out.println("时间:" + sdf.format(date));
        System.out.println("状态占用");
        System.out.println("总数量==" + j);
        List<Smelter> smelterList = smelterService.getSmelterList(null);
        Smelter smelter = new Smelter();
        smelter.setS_heatnumber("4号炉");
        smelter.setS_sum(12);
        smelterService.addSmelter(smelter);
        //smelterList.toString();
        Books books1 = new Books();
        books1.setB_state(2);
        books1.setB_book("888");
        books1.setB_lot("81235");
        int i = booksService.updateBooks(books1);
        if (i > 0) {
            System.out.println("修改成功！");
        } else {
            System.out.println("修改失败！");
        }
        System.out.println("进入1getBooksList界面");
        List<Books> books = booksService.getBooksList("888", null);
        session.setAttribute("users", books);
        return "from/index1";
    }


}
