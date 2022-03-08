package com.example.common.crud;


import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/18 18:02
 */
@org.apache.ibatis.annotations.Mapper
public interface Mapper<T, K> {


    int deleteByPrimaryKey(K id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(K id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
    /**
     * 分页查询（由子类实现）
     * @param params
     * @return
     */
    Page queryPageList(Map<String, Object> params);

    /**
     * 多条件查询（由子类实现）
     * @param params
     * @return
     */
    List<Map<String,Object>> queryList(Map<String, Object> params);
}