package cn.tjy.laminated.dao.mysql.smelter;

import cn.tjy.laminated.pojo.mysql.Smelter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmelterMapper {

    /**
     * 查询所有
     * @param s_heatnumber
     * @return
     * @throws Exception
     */
    public List<Smelter> getSmelterList(@Param("s_heatnumber") String s_heatnumber) throws Exception;

    public Smelter getSmelter(@Param("s_heatnumber") String s_heatnumber) throws Exception;

    /**
     * 添加
     * @param smelter
     * @return
     */
    public int addSmelter(Smelter smelter);

    /**
     * 修改
     * @param smelter
     * @return
     * @throws Exception
     */
    public int updateSmelter(Smelter smelter) throws Exception;

    /**
     * 删除
     * @param smelter
     * @return
     */
    public int delSmelter(Smelter smelter) throws Exception;
}
