package com.expression.survey;

import com.googlecode.aviator.AviatorEvaluator;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class ExpressionTest {
    static int simple = 100;

    public static void main(String[] args) {
        aviatorTest();springELTest();//49844ms 16427ms
    }
    public static void aviatorTest() {
        long start=System.currentTimeMillis();
        int j=0;
        while (j++<10000){
            String expression= "true&&false";
            for (int i = 0; i < simple; i++) {
                expression+="&&true";
                AviatorEvaluator.execute(expression);
            }
        }
        System.out.println(System.currentTimeMillis()-start);
    }
    public static void springELTest() {
        long start=System.currentTimeMillis();
        int j=0;
        while (j++<10000){
        String expression= "true&&false";
        for (int i = 0; i < simple; i++) {
            expression+="&&true";
            //测试SpringEL解析器
            //解析表达式，如果表达式是一个模板表达式，需要为解析传入模板解析器上下文。
            //使用Expression.getValue()获取表达式的值，这里传入了Evalution上下文，第二个参数是类型参数，表示返回值的类型。
            new SpelExpressionParser().parseExpression(expression).getValue(Boolean.class);
        }}
        System.out.println(System.currentTimeMillis()-start);
    }
}
