package cn.tjy.laminated.pojo.mysql;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private Integer id;
    private String book;
    private Integer state;
}
