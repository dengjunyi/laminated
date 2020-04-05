package cn.tjy.laminated.service.mysql.feedstock;

import cn.tjy.laminated.dao.mysql.feedstock.FeedstockMapper;
import cn.tjy.laminated.pojo.mysql.Feedstock;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FeedstockServiceimpl implements FeedstockService {

    @Resource
    private FeedstockMapper feedstockMapper;
    private final static String XLS = "xls";
    public static final String XLSX = "xlsx";

    private final static Logger logger = LoggerFactory.getLogger(FeedstockServiceimpl.class);


    @Override
    public List<Feedstock> getFeedstockList(String f_book, String f_formula, Integer f_state) throws Exception {
        return feedstockMapper.getFeedstockList(f_book, f_formula, f_state);
    }

    @Override
    public List<Feedstock> getFeedstockSize() throws Exception {
        return feedstockMapper.getFeedstockSize();
    }

    @Override
    public Feedstock getFeedstockById(String f_id, String f_book) throws Exception {
        return feedstockMapper.getFeedstockById(f_id, f_book);
    }

    @Override
    public int addFeedstock(Feedstock feedstock) throws Exception {
        return feedstockMapper.addFeedstock(feedstock);
    }

    @Override
    public int updateFeedstock(Feedstock feedstock) throws Exception {
        return feedstockMapper.updateFeedstock(feedstock);
    }

    @Override
    public void batchInsert(List<Feedstock> tbagentList) {
        feedstockMapper.batchInsert(tbagentList);
    }


    public String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:// 数字
                    value = cell.getNumericCellValue() + " ";
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (date != null) {
                            value = new SimpleDateFormat("yyyy-MM-dd").format(date); //  日期格式化
                        } else {
                            value = "";
                        }
                    } else {
                        //  解析cell时候 数字类型默认是double类型的 但是想要获取整数类型 需要格式化
                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: //  字符串
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:   //  Boolean类型
                    value = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK:   // 空值
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 错误类型
                    value = "非法字符";
                    break;
                default:
                    value = "未知类型";
                    break;
            }

        }
        return value.trim();
    }








}
