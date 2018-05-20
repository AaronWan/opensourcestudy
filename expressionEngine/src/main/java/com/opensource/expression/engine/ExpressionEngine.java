package com.opensource.expression.engine;

import com.opensource.expression.engine.common.ExpressionEval;
import com.opensource.expression.engine.model.ExpressionUnit;
import com.opensource.expression.engine.model.RuleBean;
import com.opensource.expression.engine.model.ExpressionEvalResult;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/18
 * expression: (0) and (1) or (2)
 * @since 6.3
 */
@UtilityClass
public class ExpressionEngine {
    ExpressionEvalResult eval(Map<String, Object> binds, RuleBean ruleBean) {
        String expression = ruleBean.getExpression();
        List<ExpressionUnit> expressionUnits = ruleBean.getExpressionUnits();
        List<Boolean> expressionUnitValues = expressionUnits.stream().map(item -> item.eval(binds).isResult()).collect(Collectors.toList());
        return ExpressionEvalResult.ok(ExpressionEval.eval(expression, expressionUnitValues));
    }
}
