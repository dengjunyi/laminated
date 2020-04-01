package cn.tjy.laminated.pojo.mysql;

import lombok.Data;

import java.io.Serializable;

@Data
public class Smelter implements Serializable {
    private Integer s_id;
    private String s_heatnumber;
    private Integer s_sum;
    private Integer s_number;
    private String s_formula;

    @Override
    public String toString() {
        return "Smelter{" +
                "s_id=" + s_id +
                ", s_heatnumber='" + s_heatnumber + '\'' +
                ", s_sum=" + s_sum +
                ", s_number=" + s_number +
                ", s_formula='" + s_formula + '\'' +
                '}';
    }
}
