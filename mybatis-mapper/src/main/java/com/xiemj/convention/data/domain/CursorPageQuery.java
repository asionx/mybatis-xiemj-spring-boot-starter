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
 * 基于游标的分页条件查询类
 *
 * 对于大结果集的数据，使用cursor方式的目的主要是为了极大地提高性能。还是拿MySQL为例说明，比如翻页到100,000条时，不用cursor，对应的SQL为
 * select * from msgs limit 100000, 100
 * 在一个百万记录的表上，第一次执行这条SQL需要5秒以上。
 * 假定我们使用表的主键的值作为cursor_id, 使用cursor分页方式对应的SQL可以优化为
 * select * from msgs where id > cursor_id limit 100;
 * 同样的表中，通常只需要100ms以下, 效率会提高几十倍。
 * 建议Web应用中大数据集翻页可以采用这种cursor方式
 *
 * 通常适合那中按照时间顺序进行查询的
 * 参看 https://timyang.net/web/pagination/
 * @author freeway
 *
 */
public class CursorPageQuery implements CursorPageable, Serializable {
    private static final long serialVersionUID = -3336525287010509792L;

    /**
     * 默认每页显示的记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 单页最多显示记录数
     */
    private static final int MAX_PAGE_SIZE = 200;

    private long pageCursor;
    private int pageSize;

    public CursorPageQuery() {
        pageCursor = 0L;
        pageSize = DEFAULT_PAGE_SIZE;
    }

    public CursorPageQuery(long pageCursor, int pageSize) {
        if (pageCursor < 0L) {
            throw new IllegalArgumentException("Page index must not be less than one!");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.pageCursor = pageCursor;
        this.pageSize = pageSize > MAX_PAGE_SIZE ? DEFAULT_PAGE_SIZE : pageSize;
    }

    /**
     * 分页游标
     * @return
     */
    @Override
    public long getPageCursor() {
        return pageCursor;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageCursor(long pageCursor) {
        if (pageCursor < 0L) {
            throw new IllegalArgumentException("Page cursor must not be less than zero!");
        }
        this.pageCursor = pageCursor;
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
        return "CursorPageQuery{" +
                "pageCursor=" + pageCursor +
                ", pageSize=" + pageSize +
                '}';
    }
}
