package com.summary.deal;

import com.google.common.base.Splitter;
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
        exportBySeasonDiff("/details.txt");
        exportBySeasonChainRatio("/details.txt");
        exportByMonth("/details.txt");
        exportByMonthDiff("/details.txt");
        exportByMonthOfChainRatio("/details.txt");
        exportByWeekOfChainRatio("/details.txt");
    }

    @SneakyThrows
    public void exportBySeason() {
        exportByClassify("/details.txt", false, new ChartDataSupplier() {
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

    public List<List> exportByWeek(String dataFilePath, int lastWeekCount) {
        return exportByClassify(dataFilePath, false, new ChartDataSupplier() {
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
            public String getFirstColumnValue(String date) {
                List<String> times = Splitter.on("-").splitToList(date);
                return times.get(0) +"第"+times.get(1)+"周";
            }
        });
    }

    /**
     * 环比
     * @param dataFilePath
     */
    @SneakyThrows
    public void exportByWeekOfChainRatio(String dataFilePath) {
        exportChainRatio(dataFilePath, new ChartDataSupplier() {
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
            public String getFirstColumnValue(String date) {
                List<String> times = Splitter.on("-").splitToList(date);
                return times.get(0) + "第" + times.get(1)+"周";
            }
        });
    }
    /**
     * 环比
     * @param dataFilePath
     */
    @SneakyThrows
    public void exportByMonthOfChainRatio(String dataFilePath) {
        exportChainRatio(dataFilePath, new ChartDataSupplier() {
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

    public void exportBySeasonChainRatio(String dataFilePath) {
        exportChainRatio(dataFilePath, new ChartDataSupplier() {
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
     * @param dataFilePath
     */
    @SneakyThrows
    public void exportByMonth(String dataFilePath) {
        exportByClassify(dataFilePath, false, new ChartDataSupplier() {
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
     * @param dataFilePath
     */
    @Test
    @SneakyThrows
    public void exportBySeasonDiff(String dataFilePath) {
        exportByTongDiff(dataFilePath, getCurrentSeason(), new ChartDataSupplier() {
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
            public String getFirstColumnValue(String date) {
                List<String> times = Splitter.on("-").splitToList(date);
                return times.get(0) +" Q"+ times.get(1);
            }
        });
    }

    @SneakyThrows
    public void exportByMonthDiff(String dataFilePath) {
        exportByTongDiff(dataFilePath, getCurrentMonth(), new ChartDataSupplier() {
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

    public List<List> exportByDay(String dataFilePath, int day) {
        return exportByClassify(dataFilePath, false, new ChartDataSupplier() {
            @Override
            public String getClassify(DataModule data) {
                return data.getDate();
            }

            @Override
            public Comparator<? super String> getClassifyComparator() {
                return Comparator.comparingInt(o -> Integer.parseInt(o.replaceAll("-", "")));
            }

            @Override
            public String getTitle(String param) {
                return "统计趋势图(天)";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("天", "数量");
            }

            @Override
            public Integer getRecordCount() {
                return day;
            }

            @Override
            public String getFirstColumnValue(String date) {
                return date;
            }
        });
    }
}
