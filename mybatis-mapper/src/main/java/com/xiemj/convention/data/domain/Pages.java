/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */

package com.xiemj.convention.data.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页静态工厂类
 * 所有的分页对象都通过里面的静态方法来构造
 * @author freeway
 */
public final class Pages {

    /**
     * 创建分页对象
     * @param pageable 分页查询
     * @param results 数据
     * @param totalCount 总行数
     * @param converter 转换器
     * @param <T> results的数据类型
     * @param <S> 转换后的results的数据类型
     * @return Page<S>
     */
    public static <T, S> Page<S> page(Pageable pageable, List<T> results, long totalCount,
                                              Function<? super T, ? extends S> converter) {
        return page(pageable,
                Objects.nonNull(results) ?
                        results.stream().map(converter).collect(Collectors.toList()) :
                        Collections.emptyList(),
                totalCount);
    }

    /**
     * 创建常规分页对象
     * @param pageable 分页查询
     * @param results 数据
     * @param totalCount 总行数
     * @param <T> results的数据类型
     * @return Page<T>
     */
    public static <T> Page<T> page(Pageable pageable, List<T> results, long totalCount) {
        return new DefaultPage<>(pageable, results, totalCount);
    }

    /**
     * 简易版翻页
     * @param pageable 分页查询
     * @param results 数据
     * @param hasNext 是否有下一页
     * @param converter 所有的
     * @param <T> results的类型
     * @param <S> 转换后的results类型
     * @return LitePage<S>
     */
    public static <T, S> LitePage<S> litePage(Pageable pageable, List<T> results, boolean hasNext,
                                                  Function<? super T, ? extends S> converter) {
        return litePage(pageable,
                Objects.nonNull(results) ?
                        results.stream().map(converter).collect(Collectors.toList()) :
                        Collections.emptyList(), hasNext);
    }

    /**
     * 简易版翻页
     * @param pageable 分页查询
     * @param results 数据
     * @param hasNext 是否有下一页
     * @param <T> results的类型
     * @return LitePage
     */
    public static <T> LitePage<T> litePage(Pageable pageable, List<T> results, boolean hasNext) {
        return new DefaultLitePage<>(pageable, results, hasNext);
    }


    public static <T, S> CursorPage<S> cursorPage(CursorPageable pageable, List<T> results, boolean hasNext,
                                                    long nextCursor,
                                                    Function<? super T, ? extends S> converter) {
        return cursorPage(pageable,
                Objects.nonNull(results) ?
                        results.stream().map(converter).collect(Collectors.toList()) :
                        Collections.emptyList(), hasNext, nextCursor);
    }

    public static <T> CursorPage<T> cursorPage(CursorPageable pageable, List<T> results,
                                                 boolean hasNext, long nextCursor) {
        return new DefaultCursorPage<>(pageable, results, hasNext, nextCursor);
    }

}
