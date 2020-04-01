package cn.tjy.laminated.controller.mysql;

import cn.tjy.laminated.pojo.mysql.Books;
import cn.tjy.laminated.pojo.mysql.Feedstock;
import cn.tjy.laminated.pojo.mysql.Smelter;
import cn.tjy.laminated.service.mysql.books.BooksService;
import cn.tjy.laminated.service.mysql.feedstock.FeedstockService;
import cn.tjy.laminated.service.mysql.smelter.SmelterService;
import cn.tjy.laminated.service.oracle.help.HelpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping(value = "/smelter")
@Controller
public class SmelterController {

    @Resource
    private SmelterService smelterService;//获取资源
    @Resource
    private FeedstockService feedstockService;//获取资源


    @RequestMapping("/getSmelter")
    @ResponseBody
    public Smelter getBooksList(@RequestParam("s_heatnumber") String s_heatnumber) throws Exception {
        Smelter smelter = smelterService.getSmelter(s_heatnumber);
        if (smelter != null) {
            return smelter;
        } else {
            return null;
        }
    }

    @RequestMapping("/getFeedstockList")
    @ResponseBody
    public List<Feedstock> getFeedstockList(@RequestParam("f_formula") String f_formula) throws Exception {
        List<Feedstock> feedstockList = feedstockService.getFeedstockList(null,f_formula, 1);
        return feedstockList;
    }


}
