package cn.tjy.laminated.dao.mysql.produce;

import cn.tjy.laminated.pojo.mysql.Produce;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProduceMapper {

    /**
     * 查询生产表
     * @param p_heatnumber
     * @return
     * @throws Exception
     */
    public List<Produce> getProduce(@Param("p_heatnumber") String p_heatnumber) throws Exception;

    /**
     * 添加
     * @param produce
     * @return
     * @throws Exception
     */
    public int addProduce(Produce produce) throws Exception;


}
