package cn.tjy.laminated.pojo.mysql;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Display implements Serializable {

    private Integer d_id;
    private String d_heatknumber;
    private String d_lot;
    private String d_model;
    private Integer d_quantity;
    private String d_formula;
    private String d_book;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date d_time;
    private String d_operator;
    private Integer d_state;
    private String d_unlock;

    @Override
    public String toString() {
        return "Display{" +
                "d_id=" + d_id +
                ", d_heatknumber='" + d_heatknumber + '\'' +
                ", d_lot='" + d_lot + '\'' +
                ", d_model='" + d_model + '\'' +
                ", d_quantity=" + d_quantity +
                ", d_formula='" + d_formula + '\'' +
                ", d_book='" + d_book + '\'' +
                ", d_time=" + d_time +
                ", d_operator='" + d_operator + '\'' +
                ", d_state=" + d_state +
                ", d_unlock='" + d_unlock + '\'' +
                '}';
    }
}
