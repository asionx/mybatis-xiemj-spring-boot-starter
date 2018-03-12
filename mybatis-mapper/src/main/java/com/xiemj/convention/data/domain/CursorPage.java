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
 * 游标分页接口
 */
public interface CursorPage<T> extends Serializable {

    long getPageCursor();

    int getPageSize();

    long getNextCursor();

    boolean hasNext();

    List<T> getResults();

    <S> CursorPage<S> map(Function<? super T, ? extends S> converter);
}
