package cn.xilio.leopard.common.util;


import cn.xilio.leopard.common.exception.BizException;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;

/**
 * @Project Leopard
 * @Description
 * @Author xilio
 * @Website https://xilio.cn
 * @Copyright (c) 2025 xilio. All Rights Reserved.
 */
public class ResponseUtil extends HashMap<String, Object> {
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
    public ResponseUtil() {
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public ResponseUtil(int code, String msg) {
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
    public ResponseUtil(int code, String msg, Object data) {
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
    public static ResponseUtil success() {
        return ResponseUtil.success("ok");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResponseUtil success(Object data) {
        return ResponseUtil.success("ok", data);
    }


    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseUtil success(String msg, Object data) {
        return new ResponseUtil(HttpStatus.OK.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static ResponseUtil error() {
        return ResponseUtil.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static ResponseUtil error(String msg) {
        return ResponseUtil.error(msg, null);
    }

//    public static ResponseUtil error(BizException e) {
//        return ResponseUtil.error(e.getCode(), e.getMsg());
//    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static ResponseUtil error(String msg, Object data) {
        return new ResponseUtil(HttpStatus.BAD_REQUEST.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 错误消息
     */
    public static ResponseUtil error(int code, String msg) {
        return new ResponseUtil(code, msg, null);
    }

    @Override
    public ResponseUtil put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public void setData(Object data) {
        put("data", data);
    }

    public Object getData() {
        return get("data");
    }

    public String toJson(){
      return new Gson().toJson(this);
    }
}
