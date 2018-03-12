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
 * 分页对象，包含总页数和总记录数
 * @author freeway
 * @param <T>
 */
public class DefaultPage<T> implements Page<T> {
    private static final long serialVersionUID = -4756313854202560259L;

    public static final int DEFAULT_TOTAL_PAGES = 1;
    public static final int DEFAULT_TOTAL_COUNT = 0;
    /**
     * 第几页
     */
    private int pageNumber;
    /**
     * 每页多少条记录数
     */
    private int pageSize;
    /**
     * 总页
     */
    private int totalPages;
    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 结果集合
     */
    private List<T> results;

    public DefaultPage() {
        this.results = Collections.emptyList();
        this.totalPages = DEFAULT_TOTAL_PAGES;
        this.totalCount = DEFAULT_TOTAL_COUNT;
        this.pageNumber = PageQuery.DEFAULT_PAGE_NUMBER;
        this.pageSize = PageQuery.DEFAULT_PAGE_SIZE;
    }


    public DefaultPage(int pageNumber, int pageSize, List<T> results, long totalCount) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.results = Objects.nonNull(results) ? results : Collections.emptyList();
        this.totalPages = pageSize == 0 || totalCount == 0 ?
                1 : (int) Math.ceil((double) totalCount / (double) this.pageSize);
    }

    public DefaultPage(Pageable pageable, List<T> results, long totalCount) {
        this(pageable.getPageNumber(), pageable.getPageSize(), results, totalCount);
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
    public long getTotalCount() {
        return totalCount;
    }

    @Override
    public List<T> getResults() {
        return results;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public <S> Page<S> map(Function<? super T, ? extends S> converter) {
        return new DefaultPage<>(pageNumber, pageSize, Objects.nonNull(results) ?
                results.stream().map(converter).collect(Collectors.toList()) :
                Collections.emptyList(), totalCount);
    }

}
