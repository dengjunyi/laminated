package cn.tjy.laminated.controller.mysql;

import cn.tjy.laminated.pojo.mysql.Books;
import cn.tjy.laminated.pojo.mysql.Feedstock;
import cn.tjy.laminated.service.mysql.books.BooksService;
import cn.tjy.laminated.service.mysql.feedstock.FeedstockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/feedstock")
public class FeedstockController {

    @Resource
    private BooksService booksService;//获取资源
    @Resource
    private FeedstockService feedstockService;//获取资源

/*

    @RequestMapping("/index")
    public String helloSpringBoot() {
        System.out.println("进入Index界面666");
        return "index";
    }

    @RequestMapping("/from")
    public String from() {
        System.out.println("进入from界面");
        return "from/index1";
    }
*/



    @RequestMapping(value = "/feedstockList", method = RequestMethod.POST)
    @ResponseBody
    public List<Feedstock> feedstockList() throws Exception {
        List<Feedstock> feedstockList = feedstockService.getFeedstockList(null, null, null);
        return feedstockList;
    }

    @RequestMapping(value = "/getFeedstockSize", method = RequestMethod.POST)
    @ResponseBody
    public Boolean getFeedstockSize() throws Exception {
        System.out.println("判读位置是否已存满!");
        Boolean isBaen = true;
        if (feedstockService.getFeedstockSize().size() == 10) {
            isBaen = false;
        }

        return isBaen;
    }

    @RequestMapping(value = "/feedstock", method = RequestMethod.POST)
    @ResponseBody
    public Feedstock feedstock(@RequestParam("b_book") String b_book) throws Exception {
        Feedstock feedstock = new Feedstock();
        feedstock.setF_state(666);
        //判断有没有此book板
        List<Books> booksList = booksService.getBooksList(b_book, null);
        if (booksList.size() > 0) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
            System.out.println("格式化输出：" + sdf.format(date));
            System.out.println("book表里有此book板");
            int quantity = 0;
            for (Books books : booksList) {
                quantity = books.getB_quantity() + quantity;
            }
            String b_model = booksList.get(0).getB_model();
            String b_formula = booksList.get(0).getB_formula();
            System.out.println("类型:" + b_model);
            System.out.println("配方:" + b_formula);
            System.out.println("时间:" + sdf.format(date));
            System.out.println("总数量==" + quantity);
            feedstock.setF_book(b_book);
            feedstock.setF_model(b_model);
            feedstock.setF_number(booksList.size());
            feedstock.setF_quantity(quantity);
            feedstock.setF_formula(b_formula);
            feedstock.setF_time(sdf.format(date));
            System.out.println("判读位置是否已存满!");
            if (feedstockService.getFeedstockList(null, null, 1).size() == 8) {
                System.out.println("添加未分配状态");
                feedstock.setF_state(3);
                //判断是否有未分配的数据
                List<Feedstock> feedstockList = feedstockService.getFeedstockList(null, null, null);
                int size = feedstockList.size();
                int size1 = feedstockService.getFeedstockList(null, null, 3).size();
                //两个全存在
                if (feedstockList.get(size - 2).getF_id() == 9 && feedstockList.get(size - 2).getF_id() == 10) {
                    if (size1 == 1) {
                        //有未分配的数据，添加
                        if (feedstockService.getFeedstockList(null, null, null).get(size - 2).getF_state() == 2) {
                            feedstock.setF_id((size - 2));
                            feedstockService.updateFeedstock(feedstock);
                        } else {
                            feedstock.setF_id((size - 1));
                            feedstockService.updateFeedstock(feedstock);
                        }
                    } else {
                        feedstock.setF_id((size - 2));
                        feedstockService.updateFeedstock(feedstock);
                    }

                } else if (feedstockList.get(size-1).getF_id() == 8 || feedstockList.get(size-1).getF_id() == 9) {
                    feedstockService.addFeedstock(feedstock);
                }
            } else {
                feedstock.setF_state(1);
                System.out.println("添加到可以分配状态");
                List<Feedstock> feedstockList = feedstockService.getFeedstockList(null, null, 2);
                if (feedstockList.size() == 0) {
                    //添加
                    System.out.println("货架上的位置都已占用!还有空位");
                    int addFeedstock = feedstockService.addFeedstock(feedstock);
                    if (addFeedstock > 0) {
                        System.out.println("添加成功！");
                    } else {
                        System.out.println("添加失败");
                    }
                } else {
                    //修改
                    if (feedstockList.size() == 1) {
                        System.out.println("剩下一个空位");
                        feedstock.setF_id(feedstockList.get(0).getF_id());
                        feedstockService.updateFeedstock(feedstock);
                    } else {
                        for (int i = 0; i <= feedstockList.size() - 1; i++) {
                            for (int j = i + 1; j <= feedstockList.size() - 1; j++) {
                                if (feedstockList.get(i).getF_id() > feedstockList.get(j).getF_id()) {
                                    int fid = feedstockList.get(i).getF_id();
                                    int fids = feedstockList.get(i).getF_id();
                                    int temp = fid;
                                    fid = fids;
                                    fids = temp;
                                }
                            }
                        }
                        System.out.println("最小值为:" + feedstockList.get(0).getF_id());
                        feedstock.setF_id(feedstockList.get(0).getF_id());
                        feedstockService.updateFeedstock(feedstock);
                    }
                }
            }
            //改变book表中book板的状态
            Books books = new Books();
            books.setB_state(3);
            books.setB_book(b_book);
            booksService.updateBooks(books);
            List<Feedstock> feedstockLists = feedstockService.getFeedstockList(b_book, null, null);
            Feedstock feedstock1 = feedstockLists.get(0);
            System.out.println("ID:" + feedstock1.getF_id());
            System.out.println("feedstock" + feedstock1);
            return feedstock1;
        }
        return feedstock;
    }


/*    @RequestMapping(value = "/getFeedstock", method = RequestMethod.POST)
    @ResponseBody
    public Feedstock getFeedstock(@RequestParam("b_book") String b_book) throws Exception {
        System.out.println("b_book:" + b_book);
        Feedstock feedstock = new Feedstock();
        List<Books> booksList = booksService.getBooksList(b_book, null);
        System.out.println(booksList.size() + "个数");
        if (booksList.size() > 0) {
            //进行添加
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
            System.out.println("格式化输出：" + sdf.format(date));
            System.out.println("book表里有此book板");
            int quantity = 0;
            for (Books books : booksList) {
                quantity = books.getB_quantity() + quantity;
            }
            String b_model = booksList.get(0).getB_model();
            String b_formula = booksList.get(0).getB_formula();
            System.out.println("类型:" + b_model);
            System.out.println("配方:" + b_formula);
            System.out.println("时间:" + sdf.format(date));
            System.out.println("总数量==" + quantity);
            feedstock.setF_book(b_book);
            feedstock.setF_model(b_model);
            feedstock.setF_number(booksList.size());
            feedstock.setF_quantity(quantity);
            feedstock.setF_formula(b_formula);
            feedstock.setF_time(sdf.format(date));
            feedstock.setF_state(1);
            List<Feedstock> feedstockList1 = feedstockService.getFeedstockList(null, null, 2);
            if (feedstockList1.size() == 0) {
                //添加
                System.out.println("货架上的位置都已占用!还有空位");
                int addFeedstock = feedstockService.addFeedstock(feedstock);
                if (addFeedstock > 0) {
                    System.out.println("添加成功！");
                } else {
                    System.out.println("添加失败");
                }
            } else {
                //修改
                if (feedstockList1.size() == 1) {
                    System.out.println("剩下一个空位");
                    feedstock.setF_id(feedstockList1.get(0).getF_id());
                    feedstockService.updateFeedstock(feedstock);
                } else {
                    for (int i = 0; i <= feedstockList1.size() - 1; i++) {
                        for (int j = i + 1; j <= feedstockList1.size() - 1; j++) {
                            if (feedstockList1.get(i).getF_id() > feedstockList1.get(j).getF_id()) {
                                int fid = feedstockList1.get(i).getF_id();
                                int fids = feedstockList1.get(i).getF_id();
                                int temp = fid;
                                fid = fids;
                                fids = temp;
                            }
                        }
                    }
                    System.out.println("最小值为:" + feedstockList1.get(0).getF_id());
                    feedstock.setF_id(feedstockList1.get(0).getF_id());
                    feedstockService.updateFeedstock(feedstock);
                }
            }
            //改变book表中book板的状态
            Books books = new Books();
            books.setB_state(3);
            books.setB_book(b_book);
            booksService.updateBooks(books);
            List<Feedstock> feedstockList = feedstockService.getFeedstockList(b_book, null, null);
            Feedstock feedstock1 = feedstockList.get(0);
            System.out.println("ID:" + feedstock1.getF_id());
            System.out.println("feedstock" + feedstock1);
            return feedstock1;
        } else {
            feedstock.setF_state(666);
            return feedstock;
        }
    }*/

    //修改状态
    @RequestMapping(value = "/updateFeedstock", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateFeedstock(@RequestParam("f_id") String f_id) throws Exception {
        Boolean isBean = false;
        Feedstock feedstock = new Feedstock();
        feedstock.setF_state(2);
        feedstock.setF_id(Integer.valueOf(f_id));
        int i = feedstockService.updateFeedstock(feedstock);
        if (i > 0) {
            System.out.println("修改成功!");
            isBean = true;

        }
        System.out.println("isBean:" + isBean);
        return isBean;
    }


}
