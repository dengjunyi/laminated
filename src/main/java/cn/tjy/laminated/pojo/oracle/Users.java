package cn.tjy.laminated.pojo.oracle;

import lombok.Data;

import java.io.Serializable;

@Data
public class Users implements Serializable {

    private String zip_code;
    private String fitip;
    private String in_ip;
    private Integer IN_TYPE;
    private String EMPLOYEE_NAME;
    private double KEY73;
    private String HINT;


}
