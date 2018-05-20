package com.opensource.expression.engine.common;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptEngineManager;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/20
 * @since 6.3
 */
@Slf4j
@UtilityClass
public class ExpressionEval {
    NashornScriptEngine engine;

    static {
        ScriptEngineManager sem = new ScriptEngineManager();
        engine = (NashornScriptEngine) sem.getEngineByName("javascript");
    }

    /**
     * (0) and (1) and (3)
     */

    public boolean eval(String expression, List<Boolean> expressionUnits) {
        try {
            expression = expression.replaceAll("or", "||").trim();
            expression = expression.replaceAll("and", "&&").trim();
            for (int i = 0; i < expressionUnits.size(); i++) {
                expression = expression.replaceAll("(" + i + ")", String.valueOf(expressionUnits.get(i)));
            }
            return (boolean) engine.eval(expression);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }
}
