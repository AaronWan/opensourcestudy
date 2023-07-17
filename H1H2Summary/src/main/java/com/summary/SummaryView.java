package com.summary;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.summary.deal.ModuleSummary;
import com.summary.deal.TotalSummary;
import com.summary.deal.util.ChartUtils;
import com.summary.deal.util.chart.model.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author wansong
 * @date 2023/7/8
 * @apiNote
 **/
public class SummaryView {
    @BeforeClass
    public static void beforeClass() {
        Paths.get("chart.html").toFile().delete();
    }

    ModuleSummary moduleSummary = new ModuleSummary();
    TotalSummary totalSummary = new TotalSummary();
    /**
     * 日常生成
     */
    @Test
    public void summary() {
        int lastWeekCount = 10;
        List<ChartDivConfigGroup> chartDivConfigGroups = Lists.newArrayList(
                new ChartDivConfigGroup("总体数据统计/周", getSummaryChart(totalSummary.exportByWeek(lastWeekCount))),
                new ChartDivConfigGroup("总体数据统计/天", getSummaryChart(totalSummary.exportByDay(7))));

        chartDivConfigGroups.add(new ChartDivConfigGroup("总体数据统计/周", getSummaryChartConfig(moduleSummary.exportByModuleAndWeek(lastWeekCount))));
        chartDivConfigGroups.add(new ChartDivConfigGroup("总体数据统计/天", getSummaryChartConfig(moduleSummary.exportByModuleAndDay(7))));
        //按模块环比 及 趋势
        chartDivConfigGroups.addAll(moduleSummary.exportModuleTrendAndChainRatioByClassify(lastWeekCount));
        chartDivConfigGroups.addAll(moduleSummary.exportModuleTrendAndChainRatioByClassifyByDate(7));

        ChartUtils.renderTheme(new ChartDivConfigTheme("展示趋势和环比情况", "趋势正常情况下是持续下降趋于平稳；环比是<0是代表降低的，最终趋于平稳", chartDivConfigGroups), StandardOpenOption.APPEND);
    }

    private List<ChartDivConfig> getSummaryChartConfig(List<List> moduleWeekData) {
        List<ChartDivConfig> summaryWeekChartConfig = Lists.newArrayList(
                new ChartDivConfig("数据", new ChartConfig(ChartType.Table)
                        .put("title", "数据").put("width", "100%")
                        .put("vAxis", ImmutableMap.of("title", "周"))
                        .put("isStacked", true), moduleWeekData),
                new ChartDivConfig("图表", new ChartConfig(ChartType.SteppedAreaChart)
                        .put("title", "图表")
                        .put("height", 400, "width", 500)
                        .put("vAxis", ImmutableMap.of("title", "周"))
                        .put("isStacked", true), moduleWeekData)
        );
        return summaryWeekChartConfig;
    }

    private List<ChartDivConfig> getSummaryChart(List<List> summaryWeekData) {
        List<ChartDivConfig> chartDivConfigs = Lists.newArrayList();

        chartDivConfigs.add(new ChartDivConfig("统计数据明细", new ChartConfig(ChartType.Table)
                .put("width", "100%")
                .put("vAxis", ImmutableMap.of("title", "周"), "hAxis", ImmutableMap.of("title", "模块"))
                .put("seriesType", "bars"), summaryWeekData));

        chartDivConfigs.add(new ChartDivConfig("趋势图", new ChartConfig(ChartType.ComboChart)
                .put("title", "趋势图")
                .put("legend", "left")
                .put("height", 400, "width", 600)
                .put("curveType", "function"), summaryWeekData));

        chartDivConfigs.add(new ChartDivConfig("同比图", new ChartConfig(ChartType.ChainRatioChart)
                .put("title", "环比")
                .put("legend", "left")
                .put("height", 400, "width", 600)
                .put("curveType", "function"), summaryWeekData));
        return chartDivConfigs;
    }
}
