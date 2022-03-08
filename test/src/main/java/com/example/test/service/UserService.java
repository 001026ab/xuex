package com.example.test.service;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/21 10:02
 */


public interface UserService {
    /**
     * 新增
     * @param t
     * @param <T>
     * @return
     */
    <T> Integer insertData(T t);
}
