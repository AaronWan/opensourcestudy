package com.opensource.expression.engine.model.operator.impl;

import com.google.common.base.Strings;


public enum StringTypeOperatorImpl {
    isEmpty() {
        @Override
        public boolean calculate(String left,String right) {
            return Strings.isNullOrEmpty(left);
        }
    },
    isNotEmpty() {
        @Override
        public boolean calculate(String left,String right) {
            return  !isEmpty.calculate(left, right);
        }
    },
    contains() {
        @Override
        public boolean calculate(String left,String right) {
            String value = left;
            if (Strings.isNullOrEmpty(value)) {
                return false;
            }
            return value.contains(right);
        }
    },
    notContains() {
        @Override
        public boolean calculate(String left,String right) {
            return !contains.calculate(left, right);
        }
    },
    eq() {
        @Override
        public boolean calculate(String left,String right) {
            if (right == null) {
                return right == left;
            }
            return right.equals(left);
        }
    },
    neq() {
        @Override
        public boolean calculate(String left,String right) {
            return !eq.calculate(left, right);
        }
    };

    public boolean calculate(String left, String right) {
        return false;
    }

    public static StringTypeOperatorImpl getByName(String name){
        StringTypeOperatorImpl[] enums = StringTypeOperatorImpl.values();
        for (StringTypeOperatorImpl anEnum : enums) {
            if(anEnum.name().equals(name)){
                return anEnum;
            }
        }
        throw new RuntimeException("string类型不支持"+name+"操作符");
    }
}