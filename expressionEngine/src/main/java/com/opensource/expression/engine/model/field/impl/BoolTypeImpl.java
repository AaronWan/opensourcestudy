package com.opensource.expression.engine.model.field.impl;

import com.opensource.expression.engine.model.ExpressionEvalResult;
import com.opensource.expression.engine.model.field.IFieldType;
import com.opensource.expression.engine.model.operator.impl.BoolTypeOperatorImpl;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/19
 * @since 6.3
 */
public class BoolTypeImpl implements IFieldType {
    @Override
    public ExpressionEvalResult calculate(Object left,String operator,Object right) {
        return calculate(() -> BoolTypeOperatorImpl.getByName(operator).calculate(Boolean.parseBoolean(left+""),Boolean.parseBoolean(right+"")), left,operator,right);
    }

}
