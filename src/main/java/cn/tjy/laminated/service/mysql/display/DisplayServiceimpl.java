package cn.tjy.laminated.service.mysql.display;

import cn.tjy.laminated.dao.mysql.display.DisplayMapper;
import cn.tjy.laminated.pojo.mysql.Display;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DisplayServiceimpl implements DisplayService {

    @Resource
    private DisplayMapper displayMapper;

    @Override
    public List<Display> getDisplayList(String d_book, String d_lot, String d_heatknumber) throws Exception {
        return displayMapper.getDisplayList(d_book,d_lot,d_heatknumber);
    }

    @Override
    public Display getDisplay(String d_book) throws Exception {
        return displayMapper.getDisplay(d_book);
    }

    @Override
    public int addDisplay(Display display) {
        return displayMapper.addDisplay(display);
    }

    @Override
    public int updateDisplay(Display display) throws Exception {
        return displayMapper.updateDisplay(display);
    }

    @Override
    public int delDisplay(Display display) throws Exception {
        return displayMapper.delDisplay(display);
    }
}
