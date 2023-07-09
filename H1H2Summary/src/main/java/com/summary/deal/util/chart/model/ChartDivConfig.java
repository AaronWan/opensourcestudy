package com.summary.deal.util.chart.model;

import com.summary.deal.util.GsonUtil;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ChartDivConfig {
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    String title;
    String data;
    ChartType type;
    String options;
    String diffOptions;

    public ChartDivConfig(String title, ChartConfig config, List<List> data) {
        this.title = title;
        this.type = config.getType();
        this.options = GsonUtil.toJson(config.getOptions());
        this.diffOptions = GsonUtil.toJson(config.getDiffOptions());
        this.data = GsonUtil.toJson(data);
    }
}