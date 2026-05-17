package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 机构管理
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@Getter
@Setter
@ToString
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 机构名称
     */
    @TableField("name")
    private String name;

    /**
     * 上级机构ID，一级机构为0
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField("last_update_by")
    private String lastUpdateBy;

    /**
     * 更新时间
     */
    @TableField("last_update_time")
    private LocalDateTime lastUpdateTime;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    @TableLogic
    @TableField("del_flag")
    private Byte delFlag;
}
