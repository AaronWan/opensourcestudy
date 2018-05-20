package com.opensource.expression.engine.model;

import com.opensource.expression.engine.model.field.ExpressionField;
import lombok.Data;

import java.util.Map;

@Data
public class ExpressionUnit {

    private ExpressionField left;
    private String operator;
    private ExpressionField right;

    public ExpressionUnit(ExpressionField left, String operator, ExpressionField right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public ExpressionEvalResult eval(Map<String, Object> binds) {
        return left.getType().calculate(getLeftValue(binds), operator,getRightValue(binds));
    }

    private Object getLeftValue(Map<String,Object> binds) {
        return left.getValue(binds);
    }

    private Object getRightValue(Map<String,Object> binds) {
        return right.getValue(binds);
    }
}