package com.opensource.expression.engine.model.field;

import com.opensource.expression.engine.model.ExpressionEvalResult;
import com.opensource.expression.engine.model.ExpressionUnit;
import com.opensource.expression.engine.model.operator.impl.BaseTypeOperatorImpl;

import java.util.Map;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/19
 * @since 6.3
 */
public interface IFieldType {
    ExpressionEvalResult calculate(Object left,String operator,Object right);

    default ExpressionEvalResult calculate(Something something, Object left,String operator,Object right) {
        if (operator.equals(BaseTypeOperatorImpl.isNone.name())
                ||
                operator.equals(BaseTypeOperatorImpl.notNone.name())) {

            return ExpressionEvalResult.ok(BaseTypeOperatorImpl.valueOf(operator)
                    .calculate(
                            left, right
                    ));
        }
        return ExpressionEvalResult.ok(something.todo());
    }

    interface Something {
        boolean todo();
    }

}
