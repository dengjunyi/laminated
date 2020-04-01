package cn.tjy.laminated.service.mysql.produce;

import cn.tjy.laminated.dao.mysql.produce.ProduceMapper;
import cn.tjy.laminated.pojo.mysql.Produce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProduceServiceimpl implements ProduceService {

    @Resource
    private ProduceMapper produceMapper;
    @Override
    public List<Produce> getProduce(String p_heatnumber) throws Exception {
        return produceMapper.getProduce(p_heatnumber);
    }

    @Override
    public int addProduce(Produce produce) throws Exception {
        return produceMapper.addProduce(produce);
    }
}
