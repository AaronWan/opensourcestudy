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


    public BaseTypeOperatorImpl getByName(String name){
        BaseTypeOperatorImpl[] enums = BaseTypeOperatorImpl.values();
        for (BaseTypeOperatorImpl anEnum : enums) {
            if(anEnum.name().equals(name)){
                return anEnum;
            }
        }
        throw new RuntimeException("公共类型不支持"+name);
    }
}