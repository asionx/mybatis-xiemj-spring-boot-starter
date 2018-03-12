/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */

package com.xiemj.convention.data.domain;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * 轻量的分页，不用查询出总记录数
 * @author freeway
 */
public interface LitePage<T> extends Serializable {

    /**
     * 获取页号
     * @return 页号
     */
    int getPageNumber();

    /**
     * 获取每页可显示的记录数
     * @return 每页可显示的记录数
     */
    int getPageSize();

    /**
     * 获取数据列表
     * @return 数据列表
     */
    List<T> getResults();

    /**
     * 是否有下一页
     * @return
     */
    boolean hasNext();

    /**
     * 映射成另外的一种分页对象
     * @param converter 转换对象的函数接口
     * @param <S> 另外的数据类型
     * @return 分页对象
     */
    <S> LitePage<S> map(Function<? super T, ? extends S> converter);

}
