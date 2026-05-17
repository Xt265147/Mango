package com.evan.mangocore.page;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页操作
 * @author Qianxun
 * @date May 17, 2026
 */
public class PageRequest {
    /**
     * 当前页码
     */
    @Getter
    @Setter
    private int pageNum=1;
    /**
     * 每页数量
     */
    @Getter
    @Setter
    private int pageSize=10;
    /**
     * 查询参数
     */
    @Getter
    @Setter
    private Map<String,Object> params = new HashMap<>();
}
