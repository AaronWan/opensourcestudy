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


    public static NumberTypeOperatorImpl getByName(String name){
        NumberTypeOperatorImpl[] enums = NumberTypeOperatorImpl.values();
        for (NumberTypeOperatorImpl anEnum : enums) {
            if(anEnum.name().equals(name)){
                return anEnum;
            }
        }
        throw new RuntimeException("Number类型不支持"+name+"操作符");
    }
}