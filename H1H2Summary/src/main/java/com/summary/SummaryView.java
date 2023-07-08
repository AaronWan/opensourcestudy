package com.summary;

import com.summary.deal.ModuleSummary;
import com.summary.deal.TotalSummary;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Paths;

/**
 * @author wansong
 * @date 2023/7/8
 * @apiNote
 **/
public class SummaryView{
    @BeforeClass
    public static void beforeClass(){
        Paths.get("chart.html").toFile().delete();
    }

    ModuleSummary moduleSummary=new ModuleSummary();
    TotalSummary totalSummary = new TotalSummary();

    @Test
    public void summary(){
        //整体趋势
        totalSummary.exportByMonth();
//        //按模块查看趋势
        moduleSummary.exportByModuleAndMonth();
//        //整体同比
        totalSummary.exportByMonthDiff();
//        //按模块同比
        moduleSummary.exportByModuleAndMonthTongDiff();
        //整体环比
        totalSummary.exportByMonthOfChainRatio();
        //按模块环比
        moduleSummary.exportByModuleAndMonthChainRatio();
    }
    @Test
    public void summaryWeek(){
        int lastWeekCount=10;
        //整体趋势
        totalSummary.exportByWeek(lastWeekCount);
//        //按模块查看趋势
        moduleSummary.exportByModuleAndWeek(lastWeekCount);
//        //按模块同比
        moduleSummary.exportByModuleAndWeekTongDiff(lastWeekCount);
        //按模块环比
        moduleSummary.exportByModuleAndWeekChainRatio(lastWeekCount);

    }
}
