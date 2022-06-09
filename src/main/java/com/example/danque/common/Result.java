package com.example.danque.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author danque
 * @date 2022/4/15
 * @desc
 */
@Data
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 4544051243334488L;

    private Integer code;

    private String message;

    private T data;

    private boolean success;

    private PageInfo<T> dataPage;

    public Result(T data, boolean success,Integer code) {
        this.data = data;
        this.success = success;
        this.code = code;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data, true, 200);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<T>(data, false,201);
    }

}
