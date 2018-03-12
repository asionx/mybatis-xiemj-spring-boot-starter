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
 * 相应码接口类
 *
 * @author iamxi
 * @version V1.0
 * @since 2017-09-15 00:04
 */
public interface ResultCode {

    /**
     * 异常响应码标识
     */
    String SYS = "SYS";

    /**
     * 通用响应码标识
     */
    String COMMON = "C";

    /**
     * 通用响应码标识
     */
    String AUTH = "AUTH";

    /**
     * 获取相应码
     */
    String code();

    /**
     * 获取响应码信息
     */
    String message();

}
