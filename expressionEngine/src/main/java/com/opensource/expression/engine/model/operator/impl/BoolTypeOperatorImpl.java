package com.opensource.expression.engine.model.operator.impl;

import com.opensource.expression.engine.model.ExpressionEvalResult;

public enum BoolTypeOperatorImpl {
    isTrue() {
        @Override
        public boolean calculate(Boolean left, Boolean right) {
            return left;
        }
    }, isFalse() {
        @Override
        public boolean calculate(Boolean left, Boolean right) {
            return left;
        }
    },eq(){
        @Override
        public boolean calculate(Boolean left, Boolean right) {
            return left.equals(right);
        }
    },neq(){
        @Override
        public boolean calculate(Boolean left, Boolean right) {
            return  !eq.calculate(left, right);
        }
    };
    public boolean calculate(Boolean left, Boolean right) {
        return false;
    }

}