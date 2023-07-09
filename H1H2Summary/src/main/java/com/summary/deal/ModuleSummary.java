package com.summary.deal;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @author wansong
 * @date 2023/7/5
 * @apiNote
 **/
@Slf4j
public class ModuleSummary extends BaseSummary {
    @Test
    public void export() {
//        exportByModuleAndSeason();
//        exportByModuleAndSeasonTongDiff();
//        exportByModuleAndSeasonChainRatio();

//        exportByModuleAndMonthCurrentYear();
//        exportByModuleAndMonthTongDiff();
//        exportByModuleAndMonthChainRatio(true);
        exportModuleTrendAndChainRatioByClassify(10);
    }

    /**
     *
     * @param lastWeekCount
     */
    public void exportModuleTrendAndChainRatioByClassify(int lastWeekCount) {
        exportModuleTrendAndChainRatioByClassify(true, new ChartDataSupplier() {
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
                return Lists.newArrayList("周","异常数");
            }

            @Override
            public String getFirstColumnValue(String year, String classifyTime) {
                return year+" 第"+classifyTime+"周";
            }

            @Override
            public Integer getRecordCount() {
                return lastWeekCount;
            }
        });
    }

    public void exportByModuleAndWeek(int lastWeekCount) {
        exportByModuleAndTime(false, new ChartDataSupplier() {
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
            public String getFirstColumnValue(String year, String classifyTime) {
                return year+" 第"+classifyTime+"周";
            }

            @Override
            public Integer getRecordCount() {
                return lastWeekCount;
            }
        });
    }

    public void exportByModuleAndSeason() {
        exportByModuleAndSeason(false);
    }

    public void exportByModuleAndSeasonCurrentYear() {
        exportByModuleAndSeason(true);
    }
    /**
     * 按模块和月 输出
     * 面积图
     * 趋势图
     * 详情信息
     */
    @SneakyThrows
    public void exportByModuleAndMonth() {
        exportByModuleAndMonth(false);
    }

    @SneakyThrows
    public void exportByModuleAndMonthCurrentYear() {
        exportByModuleAndMonth(true);
    }

    @Test
    @SneakyThrows
    public void exportByModuleAndSeasonChainRatioCurrentYear() {
        exportByModuleAndSeasonChainRatio(true);
    }

    @Test
    @SneakyThrows
    public void exportByModuleAndSeasonChainRatio() {
        exportByModuleAndSeasonChainRatio(false);
    }


    public void exportByModuleAndSeasonChainRatio(boolean onlyCurrentYear) {
        exportByModuleChainRatio(onlyCurrentYear,new ChartDataSupplier() {
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
                return param+"-统计环比图(季度)";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("季度", "环比");
            }
        });
    }

    @Test
    @SneakyThrows
    public void exportByModuleAndMonthChainRatio() {
        exportByModuleAndMonthChainRatio(false);
    }


    public void exportByModuleAndMonthChainRatio(boolean onlyCurrentYear) {
        exportByModuleChainRatio(onlyCurrentYear,new ChartDataSupplier() {
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
                return param+"-统计环比图(月)";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("月", "环比");
            }
        });
    }

    public void exportByModuleAndWeekChainRatio(int lastWeekCount) {
        exportByModuleChainRatio(true,new ChartDataSupplier() {
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
                return param+"-统计环比图(周)";
            }

            @Override
            public List getHeaders() {
                return Lists.newArrayList("周", "环比");
            }

            @Override
            public Integer getRecordCount() {
                return lastWeekCount;
            }
        });
    }
    /**
     * 当年按模块查看 总体占比情况
     * 饼图
     */
    @SneakyThrows
    public void exportByModuleAndSeason(boolean onlyCurrentYear) {
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
            public String getFirstColumnValue(String year, String classifyTime) {
                return year+" Q"+classifyTime;
            }
        });
    }

    /**
     * 按模块和月 输出
     * 面积图
     * 趋势图
     * 详情信息
     */
    @SneakyThrows
    public void exportByModuleAndMonth(boolean onlyCurrentYear) {
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
        });
    }

    /**
     * 按季度同比
     */
    @SneakyThrows
    public void exportByModuleAndSeasonTongDiff() {
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
            public String getFirstColumnValue(String year, String classifyTime) {
                return year + " Q" + classifyTime;
            }
        });
    }

    /**
     * 按月同比
     */
    @SneakyThrows
    public void exportByModuleAndMonthTongDiff() {
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
        });
    }

    public void exportByModuleAndWeekTongDiff(int lastWeekCount) {
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
        });
    }

}
