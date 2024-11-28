package org.sem4.exam.repository;

import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/28
 */
public interface Paging<T> {
    List<T> allWithPaging();
}
