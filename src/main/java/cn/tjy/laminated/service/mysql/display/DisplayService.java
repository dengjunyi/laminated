package cn.tjy.laminated.service.mysql.display;

import cn.tjy.laminated.pojo.mysql.Display;
import java.util.List;


public interface DisplayService {

    /**
     * 查询所有
     */
    public List<Display> getDisplayList(String d_book,String d_lot,String d_heatknumber) throws Exception;

    /**
     * 查询book
     */
    public Display getDisplay(String d_book) throws Exception;
    /**
     * 添加
     */
    public int addDisplay(Display display);

    /**
     * 修改
     * @param display
     */
    public int updateDisplay(Display display) throws Exception;

    /**
     * 删除
     */
    public int delDisplay(Display display) throws Exception;
}
