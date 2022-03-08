package com.example.common.util;

import com.example.common.result.ReturnResult;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/18 9:33
 */


public class ReturnResultUtil {

    /**
     * 成功
     */

    /**
     * 成功返回数据,200 代表成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ReturnResult<T> success(T data) {
        return new ReturnResult<>(true, null, "200", data);
    }

    /**
     * 成功不返回数据
     *
     * @param <T>
     * @return
     */
    public static <T> ReturnResult<T> success() {
        //return new Result<>(true, null,null, null);
        return success(null);
    }


    /**
     * 失败返回（错误信息必须返回）
     */
    /**
     * 失败返回错误信息，与错误代码
     *
     * @param errorCode
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> ReturnResult<T> error(String errorCode, String errorMessage, T data) {
        return new ReturnResult<>(false, errorMessage,errorCode , data);
    }

    /**
     * 失败返回错误信息
     *
     * @param <T>
     * @return
     */
    public static <T> ReturnResult<T> error(String errorMessage) {
        return error("405", errorMessage, null);
    }
}
