package com.opensource.expression.engine.model;

import lombok.Data;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/19
 * @since 6.3
 */
@Data
public class ExpressionEvalResult {
    boolean success;
    boolean result;
    String msg;

    private ExpressionEvalResult(boolean success, boolean rst, String msg) {
        this.success = success;
        this.result = rst;
        this.msg = msg;
    }

    public static ExpressionEvalResult ok(boolean rst) {
        return new ExpressionEvalResult(true, rst, "");
    }

    public static ExpressionEvalResult error(String msg) {
        return new ExpressionEvalResult(false, false, msg);
    }
}
