package cn.tjy.laminated.pojo.mysql;

import lombok.Data;

import java.io.Serializable;

@Data
public class Feedstock implements Serializable {
    private Integer f_id;
    private String f_book;
    private String f_model;
    private Integer f_number;
    private Integer f_quantity;
    private String f_formula;
    private String f_time;
    private Integer f_state;

    @Override
    public String toString() {
        return "Feedstock{" +
                "f_id=" + f_id +
                ", f_book='" + f_book + '\'' +
                ", f_model='" + f_model + '\'' +
                ", f_number=" + f_number +
                ", f_quantity=" + f_quantity +
                ", f_formula='" + f_formula + '\'' +
                ", f_time='" + f_time + '\'' +
                ", f_state=" + f_state +
                '}';
    }
}
