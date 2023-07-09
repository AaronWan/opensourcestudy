package com.summary.deal.util.chart.model;

import java.util.List;

/**
 * @author wansong
 * @date 2023/7/9
 * @apiNote
 **/
public class ChartDivConfigTheme {
    String title;
    String description;
    List<ChartDivConfigGroup> chartConfigGroups;

    public ChartDivConfigTheme(String title, String description, List<ChartDivConfigGroup> chartConfigGroups) {
        this.title = title;
        this.description = description;
        this.chartConfigGroups = chartConfigGroups;
    }
}
