package com.opensource.expression.engine.model.field.impl;

import com.opensource.expression.engine.model.ExpressionEvalResult;
import com.opensource.expression.engine.model.ExpressionUnit;
import com.opensource.expression.engine.model.field.IFieldType;
import com.opensource.expression.engine.model.operator.impl.NumberTypeOperatorImpl;
import com.opensource.expression.engine.model.operator.impl.StringTypeOperatorImpl;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/19
 * @since 6.3
 */
public class NumberTypeImpl implements IFieldType {

    @Override
    public ExpressionEvalResult calculate(Object left,String operator,Object right) {
        return calculate(() -> NumberTypeOperatorImpl.getByName(operator).calculate(getValue(left),getValue(right)), left,operator,right);
    }
    protected BigDecimal getValue(Object value){
        return value==null?null:new BigDecimal(String.valueOf(value));
    }
}
