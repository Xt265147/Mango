package com.evan.mangocore.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页返回结果
 */
public class PageResult {
    @Getter
    @Setter
    private int pageNum;
    @Getter
    @Setter
    private int pageSize;
    @Getter
    @Setter
    private long totalSize;
    @Getter
    @Setter
    private int totalPages;
    @Getter
    @Setter
    private List<?> content;

}
