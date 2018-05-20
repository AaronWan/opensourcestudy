package com.opensource.expression.engine.model;

import lombok.Data;

import java.util.List;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/19
 * @since 6.3
 */
@Data
public class RuleBean {
    private String expression;
    private List<ExpressionUnit> expressionUnits;
}
