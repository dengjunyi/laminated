package cn.tjy.laminated.pojo.mysql;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Produce implements Serializable {
    private Integer p_id;
    private String p_heatnumber;
    private Integer p_number;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date p_time;
    private String p_operator;

    @Override
    public String toString() {
        return "Produce{" +
                "p_id=" + p_id +
                ", p_heatnumber='" + p_heatnumber + '\'' +
                ", p_number=" + p_number +
                ", p_time=" + p_time +
                ", p_operator='" + p_operator + '\'' +
                '}';
    }
}
