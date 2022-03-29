package cn.ustc.system.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class User {

    //自增
    private Long id;

    private String username;

    private String password;

    private String salt;

    private String email;

    private Integer type;

    private Integer status;

    private String activation_code;

    private String header_url;

    private Date create_time;
}
