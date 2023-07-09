package com.summary.deal;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

/**
 * @author wansong
 * @date 2023/7/5
 * @apiNote
 **/
@Slf4j
public class TotalSummary extends BaseSummary {
    @Test
    public void exportTotalSummary() {
        exportBySeason();
        exportBySeasonDiff();
        exportBySeasonChainRatio();
        exportByMonth();
        exportByMonthDiff();
        exportByMonthOfChainRatio();
        exportByWeekOfChainRatio();
    }

    @SneakyThrows
    public void exportBySeason() {
        exportByClassify(false, new ChartDataSupplier() {
            @Override
            public String getClassify(DataModule data) {
                return data.getYearAndQ();
            }

            @Override
            public Comparator<? super String> getClassifyComparator() {
                return Comparator.comparingInt(o -> Integer.parseInt(o.replaceAll("-", "")));
            }

            @Override
            public String getTitle(String param) {
                return "统计趋势图(季度)";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("季度", "数量");
            }
        });
    }

    public List<List> exportByWeek(int lastWeekCount) {
        return exportByClassify(false, new ChartDataSupplier() {
            @Override
            public String getClassify(DataModule data) {
                return data.getYearAndWeek();
            }

            @Override
            public Comparator<? super String> getClassifyComparator() {
                return Comparator.comparingInt(o -> Integer.parseInt(o.replaceAll("-", "")));
            }

            @Override
            public String getTitle(String param) {
                return "统计趋势图(周)";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("周", "数量");
            }

            @Override
            public Integer getRecordCount() {
                return lastWeekCount;
            }

            @Override
            public String getFirstColumnValue(String year, String classifyTime) {
                return year+"第"+classifyTime+"周";
            }
        });
    }

    /**
     * 环比
     */
    @SneakyThrows
    public void exportByWeekOfChainRatio() {
        exportChainRatio(new ChartDataSupplier() {
            @Override
            public String getClassify(DataModule data) {
                return data.getYearAndWeek();
            }

            @Override
            public Comparator<? super String> getClassifyComparator() {
                return Comparator.comparingInt(o -> Integer.parseInt(o.replaceAll("-", "")));
            }

            @Override
            public String getTitle(String param) {
                return "流程团队 周统计环比图";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("周", "环比");
            }

            @Override
            public String getFirstColumnValue(String year, String classifyTime) {
                return year + "第" + classifyTime+"周";
            }
        });
    }
    /**
     * 环比
     */
    @SneakyThrows
    public void exportByMonthOfChainRatio() {
        exportChainRatio(new ChartDataSupplier() {
            @Override
            public String getClassify(DataModule data) {
                return data.getYearAndQ();
            }

            @Override
            public Comparator<? super String> getClassifyComparator() {
                return Comparator.comparingInt(o -> Integer.parseInt(o.replaceAll("-", "")));
            }

            @Override
            public String getTitle(String param) {
                return "流程团队 月统计环比图";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("月", "环比");
            }
        });
    }

    public void exportBySeasonChainRatio() {
        exportChainRatio(new ChartDataSupplier() {
            @Override
            public String getClassify(DataModule data) {
                return data.getYearAndQ();
            }

            @Override
            public Comparator<? super String> getClassifyComparator() {
                return Comparator.comparingInt(o -> Integer.parseInt(o.replaceAll("-", "")));
            }

            @Override
            public String getTitle(String param) {
                return "流程团队 季度统计环比图";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("季度", "环比");
            }
        });
    }

    /**
     * 按月输出整体趋势图
     */
    @SneakyThrows
    public void exportByMonth() {
        exportByClassify(false, new ChartDataSupplier() {
            @Override
            public String getClassify(DataModule data) {
                return data.getYearAndMonth();
            }

            @Override
            public Comparator<? super String> getClassifyComparator() {
                return Comparator.comparingInt(o -> Integer.parseInt(o.replaceAll("-", "")));
            }

            @Override
            public String getTitle(String param) {
                return "统计趋势图(月)";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("月", "数量");
            }
        });
    }

    /**
     * 整体 季度环比
     */
    @Test
    @SneakyThrows
    public void exportBySeasonDiff() {
        exportByTongDiff(getCurrentSeason(), new ChartDataSupplier() {
            @Override
            String getClassify(DataModule data) {
                return data.getYearAndQ();
            }

            @Override
            Comparator<? super String> getClassifyComparator() {
                return Comparator.comparingInt(o -> Integer.parseInt(o.replaceAll("-", "")));
            }

            @Override
            List getHeaders() {
                return Lists.newArrayList("季度", "数量");
            }

            @Override
            String getTitle(String param) {
                return "超时总体环比图(季度)";
            }

            @Override
            public String getFirstColumnValue(String year, String classifyTime) {
                return year+" Q"+ classifyTime;
            }
        });
    }

    @SneakyThrows
    public void exportByMonthDiff() {
        exportByTongDiff(getCurrentMonth(), new ChartDataSupplier() {
            @Override
            String getClassify(DataModule data) {
                return data.getYearAndMonth();
            }

            @Override
            Comparator<? super String> getClassifyComparator() {
                return Comparator.comparingInt(o -> Integer.parseInt(o.replaceAll("-", "")));
            }

            @Override
            List getHeaders() {
                return Lists.newArrayList("月", "数量");
            }

            @Override
            String getTitle(String param) {
                return "超时总体同比图(月)";
            }

        });
    }

}
