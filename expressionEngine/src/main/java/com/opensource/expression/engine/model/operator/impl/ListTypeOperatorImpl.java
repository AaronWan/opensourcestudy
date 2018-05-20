package com.opensource.expression.engine.model.operator.impl;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/20
 * @since 6.3
 */
public enum ListTypeOperatorImpl {
    in() {
        @Override
        public boolean calculate(List left, List right) {
            if (CollectionUtils.isEmpty(left) && CollectionUtils.isEmpty(right)) {
                return true;
            }
            if ((CollectionUtils.isEmpty(left) && CollectionUtils.isNotEmpty(right))) {
                return true;
            }
            if ((CollectionUtils.isNotEmpty(left) && CollectionUtils.isEmpty(right))) {
                return false;
            }

            for (Object o : left) {
                if (!right.contains(o)) {
                    return false;
                }
            }
            return true;
        }
    },
    notIn() {
        @Override
        public boolean calculate(List left, List right) {
            return !in.calculate(left, right);
        }
    },
    intersect() {
        @Override
        public boolean calculate(List left, List right) {
            Collection rst = CollectionUtils.intersection(left, right);
            return CollectionUtils.isNotEmpty(rst);
        }
    },
    disjoint() {
        @Override
        public boolean calculate(List left, List right) {
            return !intersect.calculate(left, right);
        }
    },
    contains() {
        @Override
        public boolean calculate(List left, List right) {
            if (CollectionUtils.isEmpty(left) && CollectionUtils.isEmpty(right)) {
                return true;
            }
            if ((CollectionUtils.isEmpty(left) && CollectionUtils.isNotEmpty(right))) {
                return false;
            }
            if ((CollectionUtils.isNotEmpty(left) && CollectionUtils.isEmpty(right))) {
                return true;
            }

            for (Object o : right) {
                if (!left.contains(o)) {
                    return false;
                }
            }
            return true;
        }
    },
    notContains() {
        @Override
        public boolean calculate(List left, List right) {
            return !contains.calculate(left, right);
        }
    },
    isEmpty() {
        @Override
        public boolean calculate(List left, List right) {
            return
                    CollectionUtils.isEmpty(left);
        }
    },
    isNotEmpty() {
        @Override
        public boolean calculate(List left, List right) {
            return
                    CollectionUtils.isNotEmpty(left);
        }
    },
    eq() {
        @Override
        public boolean calculate(List left, List right) {
            return
                    CollectionUtils.isEqualCollection(left, right);
        }
    },
    neq() {
        @Override
        public boolean calculate(List left, List right) {
            return
                    !CollectionUtils.isEqualCollection(left, right);
        }
    };

    public boolean calculate(List left, List right) {
        return false;
    }
}
