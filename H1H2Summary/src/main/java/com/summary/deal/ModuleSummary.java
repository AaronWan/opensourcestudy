package com.summary.deal;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.summary.deal.util.ChartUtils;
import com.summary.deal.util.chart.model.ChartDivConfigGroup;
import com.summary.deal.util.chart.model.ChartDivConfigTheme;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

/**
 * @author wansong
 * @date 2023/7/5
 * @apiNote
 **/
@Slf4j
public class ModuleSummary extends BaseSummary {
    @SneakyThrows
    @Test
    public void export() {
//        exportByModuleAndSeason();
//        exportByModuleAndSeasonTongDiff();
//        exportByModuleAndSeasonChainRatio();

//        exportByModuleAndMonthCurrentYear();
//        exportByModuleAndMonthTongDiff();
//        exportByModuleAndMonthChainRatio(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(BaseSummary.class.getResource("/details.txt").getPath())));
        String line;
        List<DataModule> dataList=Lists.newArrayList();
        while ((line = br.readLine()) != null) {
            List<String> data = Splitter.on('\t').splitToList(line);
            dataList.add(new DataModule(data.get(0), data.get(1), Integer.parseInt(data.get(2))));
        }
        exportModuleTrendAndChainRatioByClassify(dataList);
    }

    /**
     * 模块的统计：
     * 数据，趋势，环比
     * @param dataModuleList
     */
    public void exportModuleTrendAndChainRatioByClassify(List<DataModule> dataModuleList){
        List<ChartDivConfigGroup> data = exportModuleTrendAndChainRatioByClassify(10,dataModuleList);
        ChartUtils.renderTheme(new ChartDivConfigTheme("按模块展示趋势和环比情况","趋势正常情况下是持续下降趋于平稳；环比是<0是代表降低的，最终趋于平稳",data), StandardOpenOption.APPEND);
    }

    /**
     * 获取环比 图表数据
     * @param lastWeekCount
     * @return
     */
    public List<ChartDivConfigGroup> exportModuleTrendAndChainRatioByClassify(int lastWeekCount,List<DataModule> dataModuleList) {
        return exportModuleTrendAndChainRatio(true, new ChartDataSupplier() {
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
                return param;
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("周","值");
            }

            @Override
            public String getFirstColumnValue(String date) {
                List<String> times = Splitter.on("-").splitToList(date);
                return times.get(0) +" 第"+times.get(1)+"周";
            }

            @Override
            public Integer getRecordCount() {
                return lastWeekCount;
            }
        }, dataModuleList);
    }

    /**
     * 获取环比 图表数据
     * @param lastDayCount
     * @return
     */
    public List<ChartDivConfigGroup> exportModuleTrendAndChainRatioByClassifyByDate(int lastDayCount,List<DataModule> dataModuleList) {
        return exportModuleTrendAndChainRatio(true, new ChartDataSupplier() {
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
                return param;
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("天","值");
            }

            @Override
            public String getFirstColumnValue(String date) {
                return date;
            }

            @Override
            public Integer getRecordCount() {
                return lastDayCount;
            }
        }, dataModuleList);
    }
    public List<List> exportByModuleAndWeek(int lastWeekCount,List<DataModule> dataModuleList) {
        return exportByModuleAndTime(false, new ChartDataSupplier() {
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
                return "按周/模块异常日志情况";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("周");
            }

            @Override
            public String getFirstColumnValue(String date) {
                List<String> times = Splitter.on("-").splitToList(date);
                return times.get(0) +" 第"+times.get(1)+"周";
            }

            @Override
            public Integer getRecordCount() {
                return lastWeekCount;
            }
        }, dataModuleList);
    }

    public List<List> exportByModuleAndDay(int lastDayCount,List<DataModule> dataModuleList) {
        return exportByModuleAndTime(false, new ChartDataSupplier() {
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
                return "按天/模块异常日志情况";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("天");
            }

            @Override
            public String getFirstColumnValue(String date) {
                return date;
            }

            @Override
            public Integer getRecordCount() {
                return lastDayCount;
            }
        }, dataModuleList);
    }

    public void exportByModuleAndSeason(List<DataModule> dataModuleList) {
        exportByModuleAndSeason(false,dataModuleList);
    }

    public void exportByModuleAndSeasonCurrentYear(List<DataModule> dataModuleList) {
        exportByModuleAndSeason(true,dataModuleList);
    }
    /**
     * 按模块和月 输出
     * 面积图
     * 趋势图
     * 详情信息
     * @param dataModuleList
     */
    @SneakyThrows
    public void exportByModuleAndMonth(List<DataModule> dataModuleList) {
        exportByModuleAndMonth(false,dataModuleList);
    }

    @SneakyThrows
    public void exportByModuleAndMonthCurrentYear(List<DataModule> dataModuleList) {
        exportByModuleAndMonth(true,dataModuleList);
    }

    /**
     * 当年按模块查看 总体占比情况
     * 饼图
     */
    @SneakyThrows
    public void exportByModuleAndSeason(boolean onlyCurrentYear,List<DataModule> dataModuleList) {
        exportByModuleAndTime(onlyCurrentYear, new ChartDataSupplier() {
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
                return "按季度/模块异常日志情况";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("季度");
            }

            @Override
            public String getFirstColumnValue(String date) {
                List<String> times = Splitter.on("-").splitToList(date);
                return times.get(0) +" Q"+times.get(1);
            }
        }, dataModuleList);
    }

    /**
     * 按模块和月 输出
     * 面积图
     * 趋势图
     * 详情信息
     */
    @SneakyThrows
    public void exportByModuleAndMonth(boolean onlyCurrentYear,List<DataModule> dataModuleList) {
        exportByModuleAndTime(onlyCurrentYear, new ChartDataSupplier() {
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
                return "按月/模块异常日志情况";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("月");
            }
        }, dataModuleList);
    }

    /**
     * 按季度同比
     */
    @SneakyThrows
    public void exportByModuleAndSeasonTongDiff(List<DataModule> dataModuleList) {
        exportByModuleAndTimeTongDiff((int) Math.ceil((Calendar.getInstance().get(Calendar.MONTH) + 1) / 3.0), new ChartDataSupplier() {
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
                return "按季度/" + param + "异常日志情况";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("季度");
            }

            @Override
            public String getFirstColumnValue(String date) {
                List<String> times = Splitter.on("-").splitToList(date);
                return times.get(0) + " Q" + times.get(1);
            }
        }, dataModuleList);
    }

    /**
     * 按月同比
     */
    @SneakyThrows
    public void exportByModuleAndMonthTongDiff(List<DataModule> dataModuleList) {
        exportByModuleAndTimeTongDiff(Calendar.getInstance().get(Calendar.MONTH) + 1, new ChartDataSupplier() {
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
                return param+"模块-异常日志情况(月)";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("月");
            }
        }, dataModuleList);
    }

    public void exportByModuleAndWeekTongDiff(int lastWeekCount,List<DataModule> dataModuleList) {
        exportByModuleAndTimeTongDiff(getCurrentWeek(), new ChartDataSupplier() {
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
                return param+"模块-异常日志同比(周)";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("周");
            }

            @Override
            public Integer getRecordCount() {
                return lastWeekCount;
            }
        }, dataModuleList);
    }

}
