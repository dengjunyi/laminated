package cn.tjy.laminated.service.mysql.feedstock;

import cn.tjy.laminated.dao.mysql.feedstock.FeedstockMapper;
import cn.tjy.laminated.pojo.mysql.Feedstock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FeedstockServiceimpl implements FeedstockService{

    @Resource
    private FeedstockMapper feedstockMapper;


    @Override
    public List<Feedstock> getFeedstockList( String f_book,String f_formula,Integer f_state) throws Exception {
        return feedstockMapper.getFeedstockList(f_book,f_formula,f_state);
    }

    @Override
    public List<Feedstock> getFeedstockSize() throws Exception {
        return feedstockMapper.getFeedstockSize();
    }

    @Override
    public Feedstock getFeedstockById(String f_id, String f_book) throws Exception {
        return feedstockMapper.getFeedstockById(f_id,f_book);
    }

    @Override
    public int addFeedstock(Feedstock feedstock) throws Exception {
        return feedstockMapper.addFeedstock(feedstock);
    }

    @Override
    public int updateFeedstock(Feedstock feedstock) throws Exception {
        return feedstockMapper.updateFeedstock(feedstock);
    }
}
