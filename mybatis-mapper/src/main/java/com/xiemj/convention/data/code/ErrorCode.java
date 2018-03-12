/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 *
 */
package com.xiemj.convention.data.code;


/**
 * 错误编码枚举对象
 * 约定，统一以_Error结尾
 */
public enum ErrorCode implements ResultCode {

    // 未知的系统错误
    UNKNOWN_ERROR("1", "未知的系统错误"),
    // 各类异常响应码
    DB_ERROR("1", SYS, "数据库异常"),
    CACHE_ERROR("2", SYS, "缓存异常"),
    HTTP_ERROR("3", SYS, "调用HTTP接口发生异常"),
    RETURN_NULL_ERROR("4", SYS, "服务不能返回空对象");

    /**
     * 响应码
     */
    private final String code;

    /**
     * 响应消息
     */
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param code 响应码
     * @param codeSign 响应码标识
     * @param message 消息
     */
    ErrorCode(String code, String codeSign, String message) {
        this.code = codeSign + "_" + code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
