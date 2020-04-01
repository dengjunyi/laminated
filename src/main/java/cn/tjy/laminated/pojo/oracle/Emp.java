package cn.tjy.laminated.pojo.oracle;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Emp implements Serializable {
    private int deptno;
    private int result;
    private Date execDate;
}
