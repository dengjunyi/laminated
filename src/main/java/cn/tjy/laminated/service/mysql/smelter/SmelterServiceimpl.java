package cn.tjy.laminated.service.mysql.smelter;

import cn.tjy.laminated.dao.mysql.smelter.SmelterMapper;
import cn.tjy.laminated.pojo.mysql.Smelter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmelterServiceimpl implements SmelterService {

    @Resource
    private SmelterMapper smelterMapper;

    @Override
    public List<Smelter> getSmelterList(String s_heatnumber) throws Exception {
        return smelterMapper.getSmelterList(s_heatnumber);
    }

    @Override
    public Smelter getSmelter(String s_heatnumber) throws Exception {
        return smelterMapper.getSmelter(s_heatnumber);
    }

    @Override
    public int addSmelter(Smelter smelter) {
        return smelterMapper.addSmelter(smelter);
    }

    @Override
    public int updateSmelter(Smelter smelter) throws Exception {
        return smelterMapper.updateSmelter(smelter);
    }

    @Override
    public int delSmelter(Smelter smelter) throws Exception {
        return smelterMapper.delSmelter(smelter);
    }
}
