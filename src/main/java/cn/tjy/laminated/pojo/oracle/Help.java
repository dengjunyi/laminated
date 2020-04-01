package cn.tjy.laminated.pojo.oracle;

import lombok.Data;

import java.io.Serializable;

@Data
public class Help implements Serializable {

    private Integer id;
    private String TOPIC;
    private int SEQ;
    private String INFO;
}
