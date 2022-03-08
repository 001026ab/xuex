package com.example.common.util;


import com.example.common.result.MyResult;

/**
 * @author ReStartLin
 * @data 2019/6/4 15:06
 * @classDesc: 功能描述:
 */
public class MyResultUtil {

    /**
       success
     */

    /**
     * 有数据返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T> MyResult<T> success(T data) {
        return new MyResult<>(true, null, null, data);
    }
    /**
     * 成功后返回null
     */
    public static <T> MyResult<T> success() {
        return success(null);
    }


    /**
        error
     */
    public static <T> MyResult<T> error(String errorCode, String errorMsg, T data) {
        return new MyResult<>(false, errorCode, errorMsg, data);
    }

    /*public static <T> MyResult<T> error(ResultBaseEnum resultEnum, T data) {
        return error(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public static <T> MyResult<T> error(ResultBaseEnum resultEnum) {
        return error(resultEnum, null);
    }
*/

    /**
     * 返回失败信息
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> MyResult<T> error(String msg) {
        return error("400", msg, null);
    }

}
