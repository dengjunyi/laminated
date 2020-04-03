package cn.tjy.laminated.controller.mysql;

import cn.tjy.laminated.config.ImportService;
import cn.tjy.laminated.dao.mysql.feedstock.FeedstockMapper;
import cn.tjy.laminated.pojo.mysql.Book;
import cn.tjy.laminated.pojo.mysql.Books;
import cn.tjy.laminated.pojo.mysql.Feedstock;
import cn.tjy.laminated.pojo.mysql.Smelter;
import cn.tjy.laminated.pojo.oracle.Users;
import cn.tjy.laminated.service.mysql.book.bookService;
import cn.tjy.laminated.service.mysql.books.BooksService;
import cn.tjy.laminated.service.mysql.feedstock.FeedstockService;
import cn.tjy.laminated.service.mysql.smelter.SmelterService;
import cn.tjy.laminated.service.oracle.help.HelpService;
import cn.tjy.laminated.service.oracle.users.UsersService;

import cn.tjy.laminated.util.sound;
import oracle.sql.NUMBER;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Resource
    private FeedstockService feedstockService;//获取资源


    //跳转主界面
    @RequestMapping("/index")
    public String idnexs() {
        return "index";
    }

    //跳转功能列表  fun
    @RequestMapping("/fun")
    public String fun() {


       /* Map map = new HashMap();
        map.put("zip_code", "");
        map.put("fitip", "DMC");
        map.put("in_ip", "192.168.1.102");
        map.put("IN_TYPE", 1);
        usersService.getUsers(map);
        System.out.println("调用成功");
        System.out.println("lot号:" + map.get("KEY73"));
        System.out.println("EMPLOYEE_NAME:" + map.get("EMPLOYEE_NAME"));
        System.out.println("HINT:" + map.get("HINT"));
 *//*  I_W0           IN  VARCHAR2, -- LOT
    I_DIV          IN  VARCHAR2, -- DIV_CODE
    O_FLAG         OUT NUMBER,   -- 0: 错误  1: 正确
    O_PROC         OUT VARCHAR2, -- 配方
    O_CPNUMBER     OUT VARCHAR2, -- 型号
    O_QTY          OUT NUMBER,   -- 数量
    O_HINT         OUT VARCHAR2  -- 错误提示*//*

        Map map1 = new HashMap();
        map1.put("I_W0", "123");
        map1.put("I_DIV", "");
        usersService.lot(map1);
        System.out.println("调用成功");
        System.out.println("判断:" + map1.get("O_FLAG"));
        // System.out.println("EMPLOYEE_NAME:" + map.get("EMPLOYEE_NAME"));
        System.out.println("错误提示:" + map1.get("O_HINT"));


        System.out.println("登录判断!");
        // System.out.println("员工号:" + username);
        Boolean isBaen = false;
        Map ma2 = new HashMap();
        ma2.put("I_USER_CODE", "888");
        ma2.put("I_TYPE", "1");
        ma2.put("I_DIV", "");
        usersService.login(ma2);
        System.out.println("调用成功");
        System.out.println("员工号:" + ma2.get("O_USER__NAME"));
        System.out.println("状态:" + ma2.get("O_KEY73"));
        System.out.println("提示:" + ma2.get("O_HINT"));*/
        return "from/fun";
    }

    //登录  login
    @RequestMapping("/login")
    public String login() {
        System.out.println("登录!");
        return "from/login";
    }

    @RequestMapping(value = "/logins", method = RequestMethod.POST)
    @ResponseBody
    public Boolean logins(@RequestParam("username") String username) {
        System.out.println("登录判断!");
        System.out.println("员工号:" + username);
        Boolean isBaen = false;
        Map map = new HashMap();
        map.put("I_USER_CODE", username);
        map.put("I_TYPE", "1");
        map.put("I_DIV", "");
        booksService.login(map);
        System.out.println("调用成功");
        System.out.println("员工号:" + map.get("O_USER__NAME"));
        System.out.println("状态:" + map.get("O_KEY73"));
        System.out.println("提示:" + map.get("O_HINT"));
        if (map.get("O_KEY73").equals("1")) {
            isBaen = true;
        }
        return isBaen;
    }


    //录入book页面
    //跳转功能列表  fun
    @RequestMapping("/book")
    public String book() {
        System.out.println("进入book页面!");
        return "from/book";
    }


    //绑定book和lot
    @RequestMapping("/info")
    public String info(@RequestParam(name = "cmd") String cmd, @RequestParam(name = "status") Integer status) {
        System.out.println("cmd:" + cmd);
        System.out.println("status:" + status);
        System.out.println("进入信息比对界面");
        return "from/info";
    }

    //信息比对1  判断book是否存在
    @RequestMapping(value = "/Boolean", method = RequestMethod.POST)
    @ResponseBody
    public Boolean isTrue(@RequestParam("book") String book) throws Exception {
        Boolean aBoolean = bookService.getBoolean(book);
        System.out.println("aBoolean" + aBoolean);
        return aBoolean;
    }

    public String valueOf(Object obj) {
        return (obj == null) ? "null" : obj.toString();
    }

    //判断lot
    @RequestMapping(value = "/lot", method = RequestMethod.POST)
    @ResponseBody
    public String lot(@RequestParam("book") String book, @RequestParam("lot") String lot) throws Exception {
        System.out.println("绑定lot");
        System.out.println("book:" + book);
        System.out.println("lot:" + lot);
        String str = "绑定成功";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        System.out.println("格式化输出：" + sdf.format(date));
        Books books1 = booksService.getBooks(book, null,1);
        Books books2 = booksService.getBooks(book, lot, 2);
        Books books3 = booksService.getBooks(book, lot,1);
        if(books2!=null || books3!=null){
            str="已重复错误信息!";
        }else {
            //调用存储过程
            Map map = new HashMap();
            map.put("I_W0", lot);
            map.put("I_DIV", "");
            usersService.lot(map);
            System.out.println("调用成功");
            System.out.println("判断:" + map.get("O_FLAG"));
            System.out.println("错误提示:" + map.get("O_HINT"));
            System.out.println("配方:" + (String) map.get("O_PROC"));
            System.out.println("型号:" + (String) map.get("O_CPNUMBER"));
            String b_formula = (String) map.get("O_PROC");//配方
            String b_model = (String) map.get("O_CPNUMBER");//型号
            Integer b_quantity = (Integer) map.get("O_QTY");//数量
            Integer b_state = 1;
            map.get("O_FLAG");

            if (valueOf(map.get("O_FLAG")).equals("0")) {
                b_state = 2;
                str = String.valueOf(map.get("O_HINT"));
            } else {
                System.out.println("正确的Lot号");
                if (books1 != null) {
                    System.out.println(books1);
                    try {
                        if (books1.getB_formula().equals(b_formula)) {
                            System.out.println("配方一致!");
                        } else {
                            b_state = 2;
                            System.out.println("配方不一致!");
                            str = "配方不一致";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("空指针");
                    }
                } else {
                    System.out.println("有值");
                }
            }
            //添加到books表
            Books books = new Books();
            books.setB_book(book);
            books.setB_lot(lot);
            books.setB_model(b_model);
            books.setB_quantity(b_quantity);
            books.setB_formula(b_formula);
            books.setB_time(sdf.format(date));
            books.setB_operator("张三");
            books.setB_state(b_state);
            books.setB_unlock("");


            int i = booksService.addBooks(books);
            if (i == 1) {
                System.out.println("添加成功!");
            } else {
                System.out.println("添加失败!");
            }

        }


        System.out.println(str);
        return str;
    }


    //声音
    @RequestMapping(value = "/sound", method = RequestMethod.POST)
    @ResponseBody
    public void sound(@RequestParam("name") String name) throws Exception {
        System.out.println("book:" + name);
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


    //录入book页面
    //跳转功能列表  fun
    @RequestMapping("/exe")
    public String exe() {
        System.out.println("进入book页面!");
        return "dao";
    }

    //导出
    @RequestMapping(value = "UserExcelDownloads", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        List<Feedstock> classmateList = feedstockService.getFeedstockList(null, null, 1);
        //List<Book> classmateList = bookService.getBook();

        String fileName = "feedstock" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = {"f_id", "f_book", "f_model", "f_number", "f_quantity", "f_formula", "f_time", "f_state"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Feedstock teacher : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(teacher.getF_id());
            row1.createCell(1).setCellValue(teacher.getF_book());
            row1.createCell(2).setCellValue(teacher.getF_model());
            row1.createCell(3).setCellValue(teacher.getF_number());
            row1.createCell(4).setCellValue(teacher.getF_quantity());
            row1.createCell(5).setCellValue(teacher.getF_formula());
            row1.createCell(6).setCellValue(teacher.getF_time());
            row1.createCell(7).setCellValue(teacher.getF_state());
            //row1.createCell(3).setCellValue(teacher.getTpassword());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    @Autowired
    private ImportService importService;
    @Resource
    private FeedstockMapper feedstockMapper;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    // @Transactional(rollbackFor = Exception.class)
    public String uploadExcel(@RequestParam MultipartFile file, HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("名字:" + file);
        InputStream inputStream = null;
        List<List<Object>> list = null;
        //MultipartFile file = multipartRequest.getFile("filename");
        if (file.isEmpty()) {
            return "文件不能为空";
        }
       /* if (xlsFile.isEmpty()) {
            result.put("code", 500);
            result.put("message", "导入文件为空！");
            return result;
        }*/

        inputStream = file.getInputStream();
        list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();
//连接数据库部分
        for (int i = 0; i < list.size(); i++) {
            List<Object> lo = list.get(i);
            Feedstock feedstock = new Feedstock();
            //feedstock.setF_id((Integer) lo.get(0));
            feedstock.setF_book(String.valueOf(lo.get(0)));
            feedstock.setF_model(String.valueOf(lo.get(1)));
            feedstock.setF_number((Integer) lo.get(2));
            feedstock.setF_quantity((Integer) lo.get(3));
            feedstock.setF_formula(String.valueOf(lo.get(4)));
            feedstock.setF_time(String.valueOf(lo.get(5)));
            feedstock.setF_state((Integer) lo.get(6));
            feedstockMapper.addFeedstock(feedstock);
            //调用mapper中的insert方法
        }
        return "上传成功";
    }


}
