package com.summary.deal.util.chart.model;

import lombok.Data;

import java.util.List;

/**
 * 一组是一类事情
 */
@Data
public class ChartDivConfigGroup{
    private String title;
    private List<ChartDivConfig> chartConfigs;

    public ChartDivConfigGroup(String title, List<ChartDivConfig> chartConfigs) {
        this.title = title;
        this.chartConfigs = chartConfigs;
    }
}
