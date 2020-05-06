package com.travel.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;

/**
 * 注释.
 *
 * @author soddygo
 * @since 2018/5/18
 */
@Getter
@Setter
public class HttpResult implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 0000:成功
     */
    private String code;

    /**
     * 生产重构,统一用message全称
     */
    private String message;
    /**
     * 多点内部接口返回的对象json(如果有返回值的话)
     */
    private Object data;

    /**
     * 时间戳
     */
    private Date timestamp;


    public HttpResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 返回结果是否成功
     *
     * @return 布尔
     */
    public boolean isSuccess() {
        return "0000".equals(this.code);
    }

    public static HttpResult create(String code, String msg, Object obj) {
        return new HttpResult(code, msg, obj);
    }


    public static HttpResult create(String code, String result) {
        return create(code, result, (Object) null);
    }

    public static HttpResult success() {
        return success("操作成功!");
    }

    public static HttpResult success(String msg) {
        return create("0000", msg);
    }

    public static HttpResult success(String msg, Object obj) {
        return create("0000", msg, obj);
    }

    public static HttpResult failure() {
        return failure("操作失败!");
    }

    public static HttpResult failure(String msg) {
        return create("-1", msg);
    }


    public static HttpResult failure(Exception ex) {
        return failure("系统异常:" + ex.getMessage(), ex);
    }

    public static HttpResult failure(String message, Exception ex) {
        if (ex == null) {
            return failure();
        } else {
            HttpResult msg = failure(message);

            try {
                StringWriter sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                msg.setData(sw.toString());
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            return msg;
        }
    }

    @Override
    public String toString() {
        return "HttpResult [code=" + this.code + ", message=" + this.message + ", data=" + this.data + "]";
    }
}
