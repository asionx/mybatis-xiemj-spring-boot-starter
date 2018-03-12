/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.xiemj.convention.data.domain;

import com.xiemj.convention.data.code.SuccessCode;

/**
 *  Result工具类，用于返回Result对象
 *  @author freeway
 */
public final class Results {

    /**
     * 成功
     * @return Result<Void>
     */
    public static Result<Void> success() {
        return new DefaultResult<Void>()
                .setCode(SuccessCode.SUCCESS.code())
                .setMessage(SuccessCode.SUCCESS.message());
    }

    /**
     * 成功
     * @param data 并设置data参数
     * @param <T> data的泛型
     * @return Result<T>
     */
    public static <T> Result<T> success(T data) {
        return new DefaultResult<T>()
                .setCode(SuccessCode.SUCCESS.code())
                .setMessage(SuccessCode.SUCCESS.message()).setData(data);
    }



    public static <T> Result<T> invalid(String message) {
        return new DefaultResult<T>().setCode("0").setMessage(message);
    }





    /**
     *
     * 返回带异常信息的响应结果，可以自己明确的系统错误
     *
     * @param code 错误编号
     * @param message 错误信息
     * @param errorClass 错误类名
     * @param <T> 对应data字段的数据类型
     * @return result 对象
     */
    public static <T> Result<T> error(String code, String message, String errorClass) {
        return new DefaultResult<T>()
                .setCode(code)
                .setMessage(message)
                .setErrorClass(errorClass);
    }

    /**
     * 构建参数验证失败的项目
     * @param field 字段名称
     * @param message 信息
     * @return 参数验证失败的项目
     */
    public static Result.ViolationItem buildViolationItem(String field, String message) {
        return new DefaultResult.DefaultVioationItem(field, message);
    }
}
