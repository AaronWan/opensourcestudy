package com.summary;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.summary.deal.BaseSummary;
import com.summary.deal.ModuleSummary;
import com.summary.deal.TotalSummary;
import com.summary.deal.util.ChartUtils;
import com.summary.deal.util.chart.model.*;
import lombok.SneakyThrows;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
    @SneakyThrows
    @Test
    public void export() {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(BaseSummary.class.getResource("/details.txt").getPath())));
        String line;
        List<BaseSummary.DataModule> dataList=Lists.newArrayList();
        while ((line = br.readLine()) != null) {
            List<String> data = Splitter.on('\t').splitToList(line);
            dataList.add(new BaseSummary.DataModule(data.get(0), data.get(1), Integer.parseInt(data.get(2))));
        }
        summary(dataList);
    }

    /**
     * 日常生成
     * @param dataList
     */
    public void summary(List<BaseSummary.DataModule> dataList) {
        int lastWeekCount = 10;
        List<ChartDivConfigGroup> chartDivConfigGroups = Lists.newArrayList(
                new ChartDivConfigGroup("总体数据统计/周", getSummaryChart(totalSummary.exportByWeek(dataList, lastWeekCount))),
                new ChartDivConfigGroup("总体数据统计/天", getSummaryChart(totalSummary.exportByDay(dataList, 7))));

        chartDivConfigGroups.add(new ChartDivConfigGroup("总体数据统计/周", getSummaryChartConfig(moduleSummary.exportByModuleAndWeek(lastWeekCount,dataList))));
        chartDivConfigGroups.add(new ChartDivConfigGroup("总体数据统计/天", getSummaryChartConfig(moduleSummary.exportByModuleAndDay(7,dataList))));
        //按模块环比 及 趋势
        chartDivConfigGroups.addAll(moduleSummary.exportModuleTrendAndChainRatioByClassify(lastWeekCount,dataList));
        chartDivConfigGroups.addAll(moduleSummary.exportModuleTrendAndChainRatioByClassifyByDate(7,dataList));

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
