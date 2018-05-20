package com.opensource.expression.engine.model.field;

import java.util.Map;
import java.util.Objects;

public class ExpressionField {
    ExpressionFieldType type;
    String name;
    Object value;

    public ExpressionField(ExpressionFieldType type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public Object getValue(Map<String, Object> binds) {
        if (Objects.isNull(value)) {
            return binds.get(name);
        }
        return value;
    }

    public ExpressionFieldType getType() {
        return type;
    }

    public void setType(ExpressionFieldType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setValue(Object value) {
        this.value = value;
    }
}