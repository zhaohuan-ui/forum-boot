package com.forum.common.utils.result;

/**
 *  返回HttpResult封装的数据
 */
public class HttpResultUtil {

    public HttpResultUtil() {
    }

    /**
     *  返回任意信息
     * @param <T> 任意类型
     * @param code 状态码
     * @param message 信息
     * @param data 返回的数据
     */
    public static <T> HttpResult<Object> result(int code, String message, T data){
        // 创建封装数据对象
        HttpResult<T> result = new HttpResult<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return (HttpResult<Object>) result;
    }

    /**
     *  返回成功信息,带有数据
     * @param <T> 任意类型
     * @param message
     * @param data 返回的数据
     */
    public static <T> HttpResult<Object> success(String message, T data){
        return result(200,message,data);
    }

    /**
     *  返回成功信息,不带有数据
     * @param <T> 任意类型
     * @param message 信息
     */
    public static <T> HttpResult<Object> success(String message){
        return result(200,message,null);
    }

    /**
     *  返回失败信息,不带有数据
     * @param <T> 任意类型
     * @param message
     */
    public static <T> HttpResult<Object> error(String message){
        return result(500, message, null);
    }

    public static <T> HttpResult<Object> error(String message, T data){
        return result(500, message, data);
    }
    public static <T> HttpResult<Object> post(String message){
        return result(200, message,null);
    }
}
