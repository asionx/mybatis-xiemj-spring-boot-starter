/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 *
 */
package com.xiemj.convention.data.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 游标分页对象
 * @author freeway
 * @param <T>
 */
public class DefaultCursorPage<T> implements CursorPage<T> {

    private static final long serialVersionUID = -892140241307889887L;
    /**
     * 当前页面对应的游标
     */
    private long pageCursor;
    private int pageSize;
    /**
     * 下一页对应的游标
     */
    private long nextCursor;
    private boolean hasNext;
    /**
     * 查询结果集合
     */
    private List<T> results;

    public DefaultCursorPage() {
        this.pageSize = CursorPageQuery.DEFAULT_PAGE_SIZE;
        this.results = Collections.emptyList();
    }

    public DefaultCursorPage(long pageCursor, int pageSize, List<T> results,
                             boolean hasNext, long nextCursor) {
        this.pageCursor = pageCursor;
        this.pageSize = pageSize;
        this.results = Objects.nonNull(results)? results: Collections.emptyList();
        this.nextCursor = nextCursor;
        this.hasNext = hasNext;
    }

    public DefaultCursorPage(CursorPageable pageable, List<T> results,
                             boolean hasNext, long nextCursor) {
        this(pageable.getPageCursor(), pageable.getPageSize(), results, hasNext, nextCursor);
    }

    @Override
    public long getPageCursor() {
        return pageCursor;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getNextCursor() {
        return nextCursor;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public List<T> getResults() {
        return results;
    }

    @Override
    public <S> CursorPage<S> map(Function<? super T, ? extends S> converter) {
        return new DefaultCursorPage<>(pageCursor, pageSize,
                Objects.nonNull(results) ?
                        results.stream().map(converter).collect(Collectors.toList()) :
                        Collections.emptyList(), hasNext, nextCursor);
    }

}
