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
 * 响应码对照表
 *
 * @author iamxi
 * @version V1.0
 * @since 2017-08-26 22:25
 *
 * @see <a href="http://confluence.guahao-inc.com/pages/viewpage.action?pageId=32090981">相应码规范</>
 * @see <a href="http://confluence.guahao-inc.com/pages/viewpage.action?pageId=32091062">通用列表</>
 */
public enum SuccessCode implements ResultCode {

    /**
     * 成功返回的编码
     */
    SUCCESS("0", "成功!");

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
     * @param message 消息
     */
    SuccessCode(String code, String message) {
        this.code = code;
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
