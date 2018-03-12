/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 *
 */
package com.xiemj.convention.data.domain;

/**
 * 可以进行游标分页的对象
 * @author freeway
 */
public interface CursorPageable {

    long getPageCursor();

    int getPageSize();

    void setPageCursor(long pageCursor);

    void setPageSize(int pageSize);
}
