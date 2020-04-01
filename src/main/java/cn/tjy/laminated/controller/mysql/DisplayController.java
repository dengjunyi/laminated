package cn.tjy.laminated.controller.mysql;

import cn.tjy.laminated.pojo.mysql.Books;
import cn.tjy.laminated.pojo.mysql.Display;
import cn.tjy.laminated.pojo.mysql.Smelter;
import cn.tjy.laminated.service.mysql.books.BooksService;
import cn.tjy.laminated.service.mysql.display.DisplayService;
import cn.tjy.laminated.service.mysql.produce.ProduceService;
import cn.tjy.laminated.service.mysql.smelter.SmelterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/display")
public class DisplayController {


    @Resource
    private BooksService booksService;//获取资源
    @Resource
    private DisplayService displayService;//获取资源
    @Resource
    private SmelterService smelterService;//获取资源
    @Resource
    private ProduceService produceService;//获取资源

   /* @RequestMapping("/get")
    @ResponseBody
    public String getBooksList(HttpSession session) throws Exception {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        System.out.println("格式化输出：" + sdf.format(d));
        String heatknumber = null;
        String ip = IpUtil.IP();
        if (ip.equals("192.168.1.103")) {
            heatknumber = "一号炉";
        } else {
            heatknumber = "二号炉";
        }
        Smelter smelter = smelterService.getSmelter(heatknumber);
        if (smelter.getS_sum().equals(smelter.getS_number())) {
            //提出警告
        } else {
            if (smelter.getS_number() == 0) {
                Books books = booksService.getBooks("888");
                Display display = new Display();
                //炉子
                display.setD_heatknumber(heatknumber);
                //lot号

                displayService.addDisplay(display);
            }
        }
        return "index";
    }*/


    //添加到进炉信息表
    @RequestMapping(value = "/addDisplay", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addDisplay(@RequestParam("d_heatknumber") String d_heatknumber, @RequestParam("d_model") String d_model, @RequestParam("d_quantity") String d_quantity, @RequestParam("d_formula") String d_formula, @RequestParam("d_book") String d_book, @RequestParam("d_time") String d_time,@RequestParam("d_operator") String d_operator, @RequestParam("d_state") String d_state,@RequestParam("d_unlock") String d_unlock) throws Exception {
        System.out.println("进入添加!");
        Boolean isBean = false;
        Display display=new Display();
        display.setD_heatknumber(d_heatknumber);
        display.setD_model(d_model);
        display.setD_quantity(Integer.valueOf(d_quantity));
        display.setD_formula(d_formula);
        display.setD_book(d_book);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        System.out.println("格式化输出：" + sdf.format(date));
        display.setD_time(date);
        display.setD_operator(d_operator);
        display.setD_state(Integer.valueOf(d_state));
        display.setD_unlock(d_unlock);
        int i = displayService.addDisplay(display);
        if (i > 0) {
            System.out.println("添加成功!");
            isBean = true;
        }
        System.out.println("isBean:" + isBean);
        return isBean;
    }


}
