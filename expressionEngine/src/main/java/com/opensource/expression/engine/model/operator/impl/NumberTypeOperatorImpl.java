package com.opensource.expression.engine.model.operator.impl;

import java.math.BigDecimal;

public enum NumberTypeOperatorImpl {
    gt() {
        @Override
        public boolean calculate(BigDecimal left, BigDecimal right) {
            if (left == null || right == null) {
                return false;
            }
            return left.subtract(right).intValue() > 0;
        }
    },
    gte() {
        @Override
        public boolean calculate(BigDecimal left, BigDecimal right) {
            if (left == null || right == null) {
                return false;
            }
            return left.subtract(right).intValue() >= 0;
        }
    },
    lt() {
        @Override
        public boolean calculate(BigDecimal left, BigDecimal right) {
            if (left == null || right == null) {
                return false;
            }
            return left.subtract(right).intValue() < 0;
        }
    },
    lte() {
        @Override
        public boolean calculate(BigDecimal left, BigDecimal right) {
            if (left == null || right == null) {
                return false;
            }
            return left.subtract(right).intValue() <= 0;
        }
    },
    eq() {
        @Override
        public boolean calculate(BigDecimal left, BigDecimal right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            return left.equals(right);
        }
    },
    neq() {
        @Override
        public boolean calculate(BigDecimal left, BigDecimal right) {
            return !eq.calculate(left, right);
        }
    };

    public boolean calculate(BigDecimal left, BigDecimal right) {
        return false;
    }
}