package com.opensource.expression.engine.model.field;

import com.opensource.expression.engine.model.ExpressionEvalResult;
import com.opensource.expression.engine.model.ExpressionUnit;
import com.opensource.expression.engine.model.field.impl.BoolTypeImpl;
import com.opensource.expression.engine.model.field.impl.ListTypeImpl;
import com.opensource.expression.engine.model.field.impl.NumberTypeImpl;
import com.opensource.expression.engine.model.field.impl.StringTypeImpl;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/19
 * @since 6.3
 */
public enum ExpressionFieldType implements IFieldType {

    bool(new BoolTypeImpl()),
    string(new StringTypeImpl()),
    list(new ListTypeImpl()),
    number(new NumberTypeImpl());

    private IFieldType fieldType;

    ExpressionFieldType(IFieldType fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public ExpressionEvalResult calculate(Object left,String operator,Object right) {
        return fieldType.calculate(left,operator,right);
    }
}
