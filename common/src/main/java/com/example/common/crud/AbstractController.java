package com.example.common.crud;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/18 17:59
 */

public abstract class AbstractController<T, K>{

    /**
     * 新增
     * @param t
     * @return
     */
    public abstract Object insert(T t);

    /**
     * 修改
     * @param t
     * @return
     */
    public abstract Object update(T t);

    /**
     * 删除
     * @param
     * @return
     */
    public abstract Object delete(K id);

    /**
     * 按主键查询
     * @param
     * @return
     */
    public abstract Object get(K Id);

    /**
     * 分页查询
     * @return
     */
    public abstract Object queryPageList(int pageSize, int pageIndex, Map<String,Object> params);

    /**
     * 多条件查询
     * @return
     */
    public abstract Object queryList(Map<String,Object> params);

}