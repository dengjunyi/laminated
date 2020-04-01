package cn.tjy.laminated.dao.oracle.help;



import cn.tjy.laminated.pojo.oracle.Help;

import java.util.List;

public interface HelpMapper {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<Help> getHelpList() throws Exception;
}
