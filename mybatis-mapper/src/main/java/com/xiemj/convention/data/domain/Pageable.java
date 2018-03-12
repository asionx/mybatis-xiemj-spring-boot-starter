/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 *
 */
package com.xiemj.convention.data.domain;


import java.io.Serializable;

/**
 * 可分页接口
 * @author freeway
 */
public interface Pageable extends Serializable {

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
     * 设置页号
     * @param pageNumber 页号
     */
    void setPageNumber(int pageNumber);

    /**
     * 设置每页可现实的记录数
     * @param pageSize  每页可现实的记录数
     */
    void setPageSize(int pageSize);

}
