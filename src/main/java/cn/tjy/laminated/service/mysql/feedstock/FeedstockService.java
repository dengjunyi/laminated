package cn.tjy.laminated.service.mysql.feedstock;

import cn.tjy.laminated.pojo.mysql.Feedstock;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedstockService {

    /**
     * 查询所有
     */
    public List<Feedstock> getFeedstockList( String f_book,String f_formula,Integer f_state) throws Exception;

    public List<Feedstock> getFeedstockSize() throws Exception;

    public Feedstock getFeedstockById(String f_id,String f_book) throws Exception;

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
