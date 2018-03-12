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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 简化版分页，不包含totalCount和totalPages
 * @author freeway
 * @param <T> 查询出来的列表集合对应的数据类型
 */
public class DefaultLitePage<T> implements LitePage<T>, Serializable {

    private static final long serialVersionUID = -7738659802203121912L;
    /**
     * 第几页
     */
    private int pageNumber;
    /**
     * 每页显示多少条记录数
     */
    private int pageSize;

    private boolean hasNext;

    private List<T> results;

    public DefaultLitePage() {
        this.pageNumber = PageQuery.DEFAULT_PAGE_NUMBER;
        this.pageSize = PageQuery.DEFAULT_PAGE_SIZE;
        this.results = Collections.emptyList();
    }

    public DefaultLitePage(Pageable pageable, List<T> results, boolean hasNext) {
        this(pageable.getPageNumber(), pageable.getPageSize(), results, hasNext);
    }

    public DefaultLitePage(int pageNumber, int pageSize, List<T> results, boolean hasNext) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.results = Objects.nonNull(results)? results: Collections.emptyList();
        this.hasNext = hasNext;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public List<T> getResults() {
        return results;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public <S> LitePage<S> map(Function<? super T, ? extends S> converter) {
        return new DefaultLitePage<>(pageNumber, pageSize,
                Objects.nonNull(results) ?
                        results.stream().map(converter).collect(Collectors.toList()) :
                        Collections.emptyList(), hasNext);
    }

    @Override
    public String toString() {
        return "DefaultLitePage{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", hasNext=" + hasNext +
                ", results=" + results +
                '}';
    }
}
