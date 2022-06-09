package com.example.danque.common.enums;

/**
 * @author danque
 * @date 2022/4/15
 * @desc
 */
public enum CodeEnum {

    SUCCES(200, "接口请求成功！"),
    FAIL(201, "接口请求失败！");

    private Integer code;
    private String message;

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer getCode() {
        return code;
    }

    private String getMessage() {
        return message;
    }
}
