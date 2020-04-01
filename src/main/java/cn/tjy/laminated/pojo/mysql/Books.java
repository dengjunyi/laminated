package cn.tjy.laminated.pojo.mysql;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class Books implements Serializable{
    private Integer b_id;
    private String b_lot;
    private String b_model;
    private Integer b_quantity;
    private String b_formula;
    private String b_book;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String b_time;
    private String b_operator;
    private Integer b_state;
    private String b_unlock;

    @Override
    public String toString() {
        return "Books{" +
                "b_id=" + b_id +
                ", b_lot='" + b_lot + '\'' +
                ", b_model='" + b_model + '\'' +
                ", b_quantity=" + b_quantity +
                ", b_formula='" + b_formula + '\'' +
                ", b_book='" + b_book + '\'' +
                ", b_time='" + b_time + '\'' +
                ", b_operator='" + b_operator + '\'' +
                ", b_state=" + b_state +
                ", b_unlock='" + b_unlock + '\'' +
                '}';
    }
}
