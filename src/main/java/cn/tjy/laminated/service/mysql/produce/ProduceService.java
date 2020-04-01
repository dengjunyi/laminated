package cn.tjy.laminated.service.mysql.produce;

import cn.tjy.laminated.pojo.mysql.Produce;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProduceService {

    /**
     * 查询生产表
     */
    public List<Produce> getProduce(String p_heatnumber) throws Exception;

    /**
     * 添加
     */
    public int addProduce(Produce produce) throws Exception;
}
