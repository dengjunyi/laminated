package cn.tjy.laminated.service.oracle.help;


import cn.tjy.laminated.dao.oracle.help.HelpMapper;
import cn.tjy.laminated.pojo.oracle.Help;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HelpServiceimpl implements HelpService {

    @Resource
    private HelpMapper helpMapper;

    @Override
    public List<Help> getHelpList() throws Exception {
        return helpMapper.getHelpList();
    }
}
