package cn.tjy.laminated.controller.mysql;

import cn.tjy.laminated.pojo.mysql.Book;
import cn.tjy.laminated.pojo.mysql.Books;
import cn.tjy.laminated.pojo.mysql.Feedstock;
import cn.tjy.laminated.pojo.mysql.Smelter;
import cn.tjy.laminated.service.mysql.books.BooksService;
import cn.tjy.laminated.service.mysql.feedstock.FeedstockService;
import cn.tjy.laminated.service.mysql.smelter.SmelterService;
import cn.tjy.laminated.service.oracle.help.HelpService;
import cn.tjy.laminated.service.oracle.users.UsersService;

import cn.tjy.laminated.util.sound;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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


    /**
     * 登录
     *
     * @return
     */
    //跳转登录界面  login
    @RequestMapping("/login")
    public String Login() {
        System.out.println("登录界面!");
        return "from/index";
    }

    @RequestMapping("/")
    public String logins() {
        System.out.println("登录界面!");
        return "from/index";
    }

    //验证登录
    @RequestMapping(value = "/logins", method = RequestMethod.POST)
    @ResponseBody
    public Boolean logins(@RequestParam("username") String username,@RequestParam("I_TYPE") String I_TYPE) {
        System.out.println("登录判断!");
        System.out.println("员工号:" + username);
        System.out.println("类型:" + I_TYPE);
        Boolean isBaen = false;
        Map map = new HashMap();
        map.put("I_USER_CODE", username);
        map.put("I_TYPE", I_TYPE);
        map.put("I_DIV", "");
        usersService.login(map);
        System.out.println("调用成功");
        System.out.println("员工号:" + map.get("O_USER__NAME"));
        System.out.println("状态:" + map.get("O_KEY73"));
        System.out.println("提示:" + map.get("O_HINT"));
        System.out.println(valueOf(map.get("O_KEY73")).equals("1"));
        if (valueOf(map.get("O_KEY73")).equals("1")) {
            isBaen = true;
        }
        System.out.println("isBaen" + isBaen);
        return isBaen;
    }


    //跳转主界面
    @RequestMapping("/from")
    public String from() {
        System.out.println("通过控制层");
        return "from/from";
    }


    //跳转功能列表  fun
    @RequestMapping("/fun")
    public String fun() {
        return "from/fun";
    }


    //录入book页面
    //跳转功能列表  fun
    @RequestMapping("/book")
    public String book() {
        System.out.println("进入book页面!");
        return "from/book";
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseBody
    public String book_is(@RequestParam("book") String book) throws Exception {
        System.out.println("添加Book信息");
        String str = "";

        Book books = new Book();
        books.setBook(book);
        books.setState(1);
        int i = bookService.addBook(books);
        if (i > 0) {
            str = "录入成功";
        } else {
            str = "录入失败!";
        }
        System.out.println("str:" + str);
        ;
        return str;
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

    //Object转字符串
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
        Books books1 = booksService.getBooks(book, null, 1);
        Books books2 = booksService.getBooks(book, lot, 2);
        Books books3 = booksService.getBooks(book, lot, 1);
        if (books2 != null || books3 != null) {
            str = "已重复错误信息!";
        } else {
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


    //  Excel导入数据到数据库
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @ResponseBody
    public String importExcel(@RequestParam("myfile") MultipartFile myFile) throws Exception {
        System.out.println("控制层");
        String str = "";
        List<Feedstock> tbagents = new ArrayList<>();
        InputStream inputStream = myFile.getInputStream();
        System.out.println("myFile:" + inputStream);
        //07年的 不兼容之前
        //XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        Workbook workbook = WorkbookFactory.create(myFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);  //示意访问sheet
        // XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

        //获取行数
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            Feedstock quChannel = new Feedstock();
            if (row.getCell(1) != null) {
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                quChannel.setF_book(row.getCell(1).getStringCellValue());
                System.out.println("book:" + row.getCell(1).getStringCellValue());
            }
            if (row.getCell(2) != null) {
                row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                System.out.println("f_model:" + row.getCell(2).getStringCellValue());
                quChannel.setF_model(row.getCell(2).getStringCellValue());

            }
            if (row.getCell(3) != null) {
                row.getCell(3).setCellType(XSSFCell.CELL_TYPE_STRING);
                System.out.println("f_number:" + row.getCell(3).getStringCellValue());
                quChannel.setF_number(Integer.valueOf(row.getCell(3).getStringCellValue()));
            }
            if (row.getCell(4) != null) {
                row.getCell(4).setCellType(XSSFCell.CELL_TYPE_STRING);
                System.out.println("f_quantity:" + row.getCell(4).getStringCellValue());
                quChannel.setF_quantity(Integer.valueOf(row.getCell(4).getStringCellValue()));
            }
            if (row.getCell(5) != null) {
                row.getCell(5).setCellType(XSSFCell.CELL_TYPE_STRING);
                System.out.println("f_formula:" + row.getCell(5).getStringCellValue());
                quChannel.setF_formula(row.getCell(5).getStringCellValue());
            }
            if (row.getCell(6) != null) {
                System.out.println("f_time:" + row.getCell(6).getStringCellValue());
                quChannel.setF_time(row.getCell(6).getStringCellValue());
            }
            if (row.getCell(7) != null) {
                row.getCell(7).setCellType(XSSFCell.CELL_TYPE_STRING);
                System.out.println("f_state:" + Integer.valueOf(row.getCell(7).getStringCellValue()));
                quChannel.setF_state(Integer.valueOf(row.getCell(7).getStringCellValue()));
            }
            tbagents.add(quChannel);
        }
        try {
            feedstockService.batchInsert(tbagents);
            str = "导入成功";
        } catch (Exception e) {
            e.printStackTrace();
            str = "导入失败";
        }

        return str;
    }


}
