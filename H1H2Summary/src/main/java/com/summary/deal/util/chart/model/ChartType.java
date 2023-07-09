package com.summary.deal.util.chart.model;

/**
 * ChainRatioChart 是环比的一个自定义类型图表， 会特殊处理数据成 同比
 * 只有 TrendChainRatio.vm 支持
 */
public enum ChartType {
    ScatterChart, LineChart, Table, PieChart, ComboChart, AreaChart, Bar, SteppedAreaChart,Histogram, ColumnChart, BarChart, ChainRatioChart
}