package cn.tjy.laminated.dao.mysql.feedstock;

import cn.tjy.laminated.pojo.mysql.Feedstock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FeedstockMapper {

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    public List<Feedstock> getFeedstockList(@Param("f_book") String f_book, @Param("f_formula") String f_formula, @Param("f_state") Integer f_state) throws Exception;

    /**
     * 查询已有数据到没有10条
     */
    public List<Feedstock> getFeedstockSize() throws Exception;

    /**
     * 通过ID，或者book号查询
     * @return
     * @throws Exception
     */
    public Feedstock getFeedstockById(@Param("f_id") String f_id,@Param("f_book") String f_book) throws Exception;

    /**
     * 添加
     */
    public int addFeedstock(Feedstock feedstock) throws Exception;

    /**
     * 修改
     */
    public int updateFeedstock(Feedstock feedstock) throws Exception;


    //execl 导入数据
    void batchInsert(List<Feedstock> tbagentList);


}
