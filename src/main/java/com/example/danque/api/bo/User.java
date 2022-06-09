package com.example.danque.api.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author danque
 * @date 2022/4/1
 * @desc
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -2083503801443301445L;

    private long id;

    private String username;

    private String password;

}
