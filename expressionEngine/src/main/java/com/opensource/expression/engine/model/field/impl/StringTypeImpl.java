package com.opensource.expression.engine.model.field.impl;

import com.opensource.expression.engine.model.ExpressionEvalResult;
import com.opensource.expression.engine.model.ExpressionUnit;
import com.opensource.expression.engine.model.field.IFieldType;
import com.opensource.expression.engine.model.operator.impl.BaseTypeOperatorImpl;
import com.opensource.expression.engine.model.operator.impl.BoolTypeOperatorImpl;
import com.opensource.expression.engine.model.operator.impl.StringTypeOperatorImpl;

import java.util.Map;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/19
 * @since 6.3
 */
public class StringTypeImpl implements IFieldType {

    @Override
    public ExpressionEvalResult calculate(Object left, String operator, Object right) {
        return calculate(() -> StringTypeOperatorImpl.getByName(operator).calculate(left == null ? null : left.toString(), right == null ? null : right.toString()), left, operator, right);
    }

}
