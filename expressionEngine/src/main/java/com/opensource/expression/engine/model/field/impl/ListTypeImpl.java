package com.opensource.expression.engine.model.field.impl;

import com.opensource.expression.engine.model.ExpressionEvalResult;
import com.opensource.expression.engine.model.ExpressionUnit;
import com.opensource.expression.engine.model.field.IFieldType;
import com.opensource.expression.engine.model.operator.impl.ListTypeOperatorImpl;
import com.opensource.expression.engine.model.operator.impl.StringTypeOperatorImpl;

import java.util.List;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/19
 * @since 6.3
 */
public class ListTypeImpl implements IFieldType {

    @Override
    public ExpressionEvalResult calculate(Object left,String operator,Object right) {
        return calculate(() -> ListTypeOperatorImpl.getByName(operator).calculate((List)left,(List)right), left,operator,right);
    }

}
