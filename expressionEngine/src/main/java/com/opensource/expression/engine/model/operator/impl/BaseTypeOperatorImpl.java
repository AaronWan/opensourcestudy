package com.opensource.expression.engine.model.operator.impl;

/**
 * @author Aaron
 */

public enum BaseTypeOperatorImpl{
    isNone() {
        @Override
        public boolean calculate(Object left, Object right) {
            return left == null;
        }
    }, notNone() {
        @Override
        public boolean calculate(Object left, Object right) {
            return left!= null;
        }
    };

    public boolean calculate(Object left, Object right) {
        return false;
    }
}