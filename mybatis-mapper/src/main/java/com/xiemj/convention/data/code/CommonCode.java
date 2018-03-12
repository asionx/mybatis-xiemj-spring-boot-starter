/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 *
 */
package com.xiemj.convention.data.code;


public enum CommonCode  {

    // 通用响应码
    INVALID_ARGS("1", "", "参数无效");


    /**
     * 响应码
     */
    private final String code;

    /**
     * 响应消息
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param code 响应码
     * @param codeSign 响应码标识
     * @param message 消息
     */
    CommonCode(String code, String codeSign, String message) {
        this.code = codeSign + "_" + code;
        this.message = message;
    }



}
