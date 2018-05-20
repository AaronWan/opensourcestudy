package com.opensource.expression.engine;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.opensource.expression.engine.model.ExpressionEvalResult;
import com.opensource.expression.engine.model.ExpressionUnit;
import com.opensource.expression.engine.model.RuleBean;
import com.opensource.expression.engine.model.field.ExpressionField;
import com.opensource.expression.engine.model.field.ExpressionFieldType;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/20
 * @since 6.3
 */
public class ExpressionEngineTest {
    @Test
    public void eval(){

        Map<String, Object> binds= Maps.newHashMap();
        binds.put("bool1",false);
        binds.put("bool2",true);
        binds.put("bool3",true);
        binds.put("bool4",false);
        RuleBean ruleBean=new RuleBean();
        ruleBean.setExpression("(0) and (1)");
        List<ExpressionUnit> expressions= Lists.newArrayList();
        expressions.add(new ExpressionUnit(new ExpressionField(ExpressionFieldType.bool,"bool1",null),"eq",new ExpressionField(ExpressionFieldType.bool,"bool2",null)));
        expressions.add(new ExpressionUnit(new ExpressionField(ExpressionFieldType.bool,"bool3",null),"neq",new ExpressionField(ExpressionFieldType.bool,"bool4",null)));
        ruleBean.setExpressionUnits(expressions);
        ExpressionEvalResult rst = ExpressionEngine.eval(binds, ruleBean);
        System.out.println(rst);
    }
}
