package cn.tjy.laminated.dao.mysql.display;

import cn.tjy.laminated.pojo.mysql.Display;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DisplayMapper {

    /**
     * 查询所有
     * @param d_book
     * @return
     * @throws Exception
     */
    public List<Display> getDisplayList(@Param("d_book") String d_book, @Param("d_lot") String d_lot,@Param("d_heatknumber") String d_heatknumber) throws Exception;

    /**
     * 查询book
     * @param d_book
     * @return
     * @throws Exception
     */
    public Display getDisplay(@Param("d_book") String d_book) throws Exception;
    /**
     * 添加
     * @param display
     * @return
     */
    public int addDisplay(Display display);

    /**
     * 修改
     * @param display
     * @return
     * @throws Exception
     */
    public int updateDisplay(Display display) throws Exception;

    /**
     * 删除
     * @param display
     * @return
     */
    public int delDisplay(Display display) throws Exception;
}
