package com.opensource;

import io.protostuff.Tag;

import java.io.Serializable;
import java.util.Map;

public class Person implements Serializable {
        @Tag(1)
        private String name;
        @Tag(2)
        private Map<String, Object> properties;

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}