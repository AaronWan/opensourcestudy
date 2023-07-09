package com.summary.deal.util.chart.model;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

@Data
public class ChartConfig {
    ChartType type;
    Map<Object, Object> options;
    Map<Object, Object> diffOptions;

    public ChartConfig(ChartType type) {
        this.type = type;
    }

    public ChartConfig put(String key, Object value) {
        getOptions().put(key, value);
        return this;
    }

    public ChartConfig put(String key, Object value, String key2, Object value2) {
        getOptions().put(key, value);
        getOptions().put(key2, value2);
        return this;
    }

    public ChartConfig diffOption(String key, Object value) {
        getDiffOptions().put(key, value);
        return this;
    }

    public ChartConfig diffOption(String key, Object value, String key2, Object value2) {
        getDiffOptions().put(key, value);
        getDiffOptions().put(key2, value2);
        return this;
    }

    public Map<Object, Object> getDiffOptions() {
        if (Objects.isNull(diffOptions)) {
            diffOptions = Maps.newHashMap();
        }
        return diffOptions;
    }

    public Map<Object, Object> getOptions() {
        if (Objects.isNull(options)) {
            options = Maps.newHashMap();
        }
        return options;
    }
}