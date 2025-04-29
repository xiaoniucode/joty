package cn.xilio.leopard.core.common.util;



import com.google.gson.Gson;
import org.springframework.util.ObjectUtils;

import java.io.Serial;
import java.util.HashMap;

/**
 * @Project Leopard
 * @Description
 * @Author xilio
 * @Website https://xilio.cn
 * @Copyright (c) 2025 xilio. All Rights Reserved.
 */
public class Result extends HashMap<String, Object> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 Result 对象，使其表示一个空消息。
     */
    public Result() {
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public Result(String code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public Result(String code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (!ObjectUtils.isEmpty(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success() {
        return Result.success(null);
    }


    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static Result success(Object data) {
        return new Result("0", LocaleUtils.getLocaleMessage("0", null), data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static Result error() {
        return Result.error("1", LocaleUtils.getLocaleMessage("1", null));
    }


    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 错误消息
     */
    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public void setData(Object data) {
        put("data", data);
    }

    public Object getData() {
        return get("data");
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

}
