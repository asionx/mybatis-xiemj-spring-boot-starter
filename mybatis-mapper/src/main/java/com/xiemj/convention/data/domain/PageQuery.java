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
 * 分页条件查询类
 * @author freeway
 */
public class PageQuery implements Pageable, Serializable {

    private static final long serialVersionUID = -8882187050736891728L;
    /**
     * 默认每页显示的记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 最大每页显示的记录数
     */
    private static final int MAX_PAGE_SIZE = 200;
    /**
     * 默认每页显示的记录数
     */
    public static final int DEFAULT_PAGE_NUMBER = 1;

    private int pageNumber;
    private int pageSize;

    public PageQuery() {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.pageNumber = DEFAULT_PAGE_NUMBER;
    }

    public PageQuery(int pageNumber, int pageSize) {
        if (pageNumber < 1) {
            throw new IllegalArgumentException("Page index must not be less than one!");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.pageNumber = pageNumber;
        this.pageSize = pageSize > MAX_PAGE_SIZE ? DEFAULT_PAGE_SIZE : pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }

    @Override
    public void setPageNumber(int pageNumber) {
        if (pageNumber < 1) {
            throw new IllegalArgumentException("Page index must not be less than one!");
        }
        this.pageNumber = pageNumber;
    }

    @Override
    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }
        this.pageSize = pageSize > MAX_PAGE_SIZE ? DEFAULT_PAGE_SIZE : pageSize;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
