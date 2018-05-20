package com.opensource.expression.engine.model.operator.impl;

public enum BoolTypeOperatorImpl {
    isTrue() {
        @Override
        public boolean calculate(Boolean left, Boolean right) {
            return left;
        }
    }, isFalse() {
        @Override
        public boolean calculate(Boolean left, Boolean right) {
            return !left;
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
    public static BoolTypeOperatorImpl getByName(String name){
        BoolTypeOperatorImpl[] enums = BoolTypeOperatorImpl.values();
        for (BoolTypeOperatorImpl anEnum : enums) {
            if(anEnum.name().equals(name)){
                return anEnum;
            }
        }
        throw new RuntimeException("Bool类型不支持"+name+"操作符");
    }
}