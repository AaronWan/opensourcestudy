package com.summary.deal;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.summary.deal.util.ChartUtils;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author wansong
 * @date 2023/7/5
 * @apiNote
 **/
@Slf4j
public class BaseSummary {
    public int getCurrentYear() {
        return 2023;
    }

    public int getCurrentMonth() {
        return 7;
    }

    public int getCurrentWeek() {
        LocalDate date = LocalDate.of(2023, 7, 1);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfWeekBasedYear());
    }

    public int getCurrentSeason() {
        return (int) Math.ceil(getCurrentMonth() / 3.0);
    }

    @BeforeClass
    public static void beforeClass() {
        Paths.get("chart.html").toFile().delete();
    }

    @SneakyThrows
    public void scanData(Consumer<DataModule> consumer) {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(BaseSummary.class.getResource("/details.txt").getPath())));
        String line;
        while ((line = br.readLine()) != null) {
            List<String> data = Splitter.on('\t').splitToList(line);
            consumer.accept(new DataModule(data.get(1), data.get(2), Integer.parseInt(data.get(5))));
        }
    }

    /**
     * 当年按模块查看 总体占比情况
     * 饼图
     */
    @SneakyThrows
    protected void exportByModuleAndTime(boolean onlyCurrentYear, ChartDataSupplier supplier) {
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Set<String> classifyList = Sets.newHashSet();
        Map<String, Map<String, Integer>> moduleAndQAndCount = Maps.newHashMap();
        scanData(data -> {
            Map<String, Integer> moduleData = moduleAndQAndCount.get(data.getModule());
            String classify = supplier.getClassify(data);
            if (Objects.nonNull(moduleData)) {
                Integer count = moduleData.get(classify);
                if (Objects.nonNull(count)) {
                    moduleData.put(classify, count + data.getCount());
                } else {
                    classifyList.add(classify);
                    moduleData.put(classify, data.getCount());
                }
            } else {
                moduleData = Maps.newLinkedHashMap();
                classifyList.add(classify);
                moduleData.put(classify, data.getCount());
                moduleAndQAndCount.put(data.getModule(), moduleData);
            }
        });
        Set<String> sortedMonth = classifyList.stream().sorted(supplier.getClassifyComparator()).collect(Collectors.toCollection(LinkedHashSet::new));

        List<List> charData = Lists.newArrayList();
        List<String> headers = supplier.getHeaders();
        headers.addAll(moduleAndQAndCount.keySet());
        charData.add(headers);
        for (String q : sortedMonth) {
            String year = Splitter.on("-").splitToList(q).get(0);
            if (onlyCurrentYear && !year.equals(currentYear + "")) {
                continue;
            }
            List row = Lists.newArrayList(supplier.getFirstColumnValue(year, Splitter.on("-").splitToList(q).get(1)));
            moduleAndQAndCount.forEach((module, value) -> row.add(Objects.isNull(value.get(q)) ? 0 : value.get(q)));
            charData.add(row);
        }

        List<ChartUtils.ChartConfig> typeAndOptions = new ArrayList();
        typeAndOptions.add(new ChartUtils.ChartConfig(ChartUtils.ChartType.Table)
                .put("width", "100%")
                .put("seriesType", "bars")
        );

        typeAndOptions.add(new ChartUtils.ChartConfig(ChartUtils.ChartType.SteppedAreaChart)
                .put("title", supplier.getTitle()).put("width", "100%", "height", "200px")
                .put("vAxis", ImmutableMap.of("title", supplier.getHeaders().get(0)))
                .put("isStacked", true)
        );

        charData = useScopeFilter(supplier, charData);
        ChartUtils.render(supplier.getTitle(), charData, typeAndOptions, StandardOpenOption.APPEND);
    }

    @SneakyThrows
    protected void exportByClassify(boolean onlyCurrentYear, ChartDataSupplier supplier) {
        Set<String> classifyList = Sets.newHashSet();
        int currentYear = getCurrentYear();
        Map<String, Integer> timeClassifyAndCount = Maps.newHashMap();
        scanData(data -> {
            String classify = supplier.getClassify(data);
            String year = Splitter.on("-").splitToList(classify).get(0);
            if (onlyCurrentYear && !year.equals(currentYear + "")) {
                return;
            }
            Integer count = timeClassifyAndCount.get(classify);
            if (Objects.nonNull(count)) {
                timeClassifyAndCount.put(classify, count + data.getCount());
            } else {
                classifyList.add(classify);
                timeClassifyAndCount.put(classify, data.getCount());
            }
        });

        Set<String> sortedQ = classifyList.stream().sorted(supplier.getClassifyComparator()).collect(Collectors.toCollection(LinkedHashSet::new));
        List<List> charData = Lists.newArrayList();
        List<String> headers = supplier.getHeaders();
        charData.add(headers);
        sortedQ.stream().map(q -> {
            List<String> times = Splitter.on("-").splitToList(q);
            return Lists.newArrayList(supplier.getFirstColumnValue(times.get(0),times.get(1)), timeClassifyAndCount.get(q));
        }).forEach(charData::add);
        List<ChartUtils.ChartConfig> typeAndOptions = Lists.newArrayList();

        typeAndOptions.add(new ChartUtils.ChartConfig(ChartUtils.ChartType.Table)
                .put("width", "100%")
                .put("vAxis", ImmutableMap.of("title", headers.size() > 1 ? headers.get(1) : headers.get(0)), "hAxis", ImmutableMap.of("title", "模块"))
                .put("seriesType", "bars")
        );

        typeAndOptions.add(new ChartUtils.ChartConfig(ChartUtils.ChartType.LineChart)
                .put("title", supplier.getTitle())
                .put("legend", "left")
                .put("curveType", "function")
        );
        charData = useScopeFilter(supplier, charData);
        ChartUtils.render(supplier.getTitle(), charData, typeAndOptions, StandardOpenOption.APPEND);
    }

    /**
     * 按模块展示趋势
     */
    @SneakyThrows
    protected void exportModuleTrendAndChainRatioByClassify(boolean onlyCurrentYear, ChartDataSupplier supplier) {
        Integer currentYear = getCurrentYear();
        Set<String> classifyList = Sets.newHashSet();
        Map<String, Map<String, Integer>> moduleAndTimeClassifyAndCount = Maps.newHashMap();
        scanData(data -> {
            Map<String, Integer> moduleData = moduleAndTimeClassifyAndCount.get(data.getModule());
            String classify = supplier.getClassify(data);
            if (Objects.nonNull(moduleData)) {
                Integer count = moduleData.get(classify);
                if (Objects.nonNull(count)) {
                    moduleData.put(classify, count + data.getCount());
                } else {
                    classifyList.add(classify);
                    moduleData.put(classify, data.getCount());
                }
            } else {
                moduleData = Maps.newLinkedHashMap();
                classifyList.add(classify);
                moduleData.put(classify, data.getCount());
                moduleAndTimeClassifyAndCount.put(data.getModule(), moduleData);
            }
        });
        Set<String> sortedMonth = classifyList.stream().sorted(supplier.getClassifyComparator()).collect(Collectors.toCollection(LinkedHashSet::new));
        List<List<ChartUtils.ChartDivConfig>> chatConfigs=Lists.newArrayList();

        List<String> headers = supplier.getHeaders();
        for (Map.Entry<String, Map<String, Integer>> entry : moduleAndTimeClassifyAndCount.entrySet()) {
            String module = entry.getKey();
            Map<String, Integer> qAndCount = entry.getValue();
            List<String> moduleHeaders = Lists.newArrayList(headers);
            moduleHeaders.add(module);
            List<List> charData = Lists.newArrayList();
            charData.add(headers);

            for (String q : sortedMonth) {
                String year = Splitter.on("-").splitToList(q).get(0);
                if (onlyCurrentYear && !year.equals(currentYear + "")) {
                    continue;
                }
                List row = Lists.newArrayList(supplier.getFirstColumnValue(year, Splitter.on("-").splitToList(q).get(1)));
                row.add(qAndCount.get(q));
                charData.add(row);
            }

            charData = useScopeFilter(supplier, charData);

            chatConfigs.add(Lists.newArrayList(
                    new ChartUtils.ChartDivConfig(supplier.getTitle(module) + "数据", new ChartUtils.ChartConfig(ChartUtils.ChartType.Table)
                            .put("title", supplier.getTitle(module))
                            .put("legend", "left")
                            .put("curveType", "function"),charData),
                    new ChartUtils.ChartDivConfig(supplier.getTitle(module) + "趋势图", new ChartUtils.ChartConfig(ChartUtils.ChartType.LineChart)
                            .put("title", supplier.getTitle(module))
                            .put("legend", "left")
                            .put("curveType", "function"),charData),
                    new ChartUtils.ChartDivConfig(supplier.getTitle(module) + "环比图",
                            new ChartUtils.ChartConfig(ChartUtils.ChartType.ChainRatioChart)
                                    .put("title", supplier.getTitle(module))
                                    .put("legend", "left")
                                    .put("curveType", "function"),charData)
                    ));
        }
        ChartUtils.renderOnlyChainRatio(chatConfigs, StandardOpenOption.APPEND);
    }

    /**
     * 环比
     */
    @SneakyThrows
    protected void exportByModuleChainRatio(boolean onlyCurrentYear, ChartDataSupplier supplier) {
        Integer currentYear = getCurrentYear();
        Set<String> classifyList = Sets.newHashSet();
        Map<String, Map<String, Integer>> moduleAndTimeClassifyAndCount = Maps.newHashMap();
        scanData(data -> {
            Map<String, Integer> moduleData = moduleAndTimeClassifyAndCount.get(data.getModule());
            String classify = supplier.getClassify(data);
            if (Objects.nonNull(moduleData)) {
                Integer count = moduleData.get(classify);
                if (Objects.nonNull(count)) {
                    moduleData.put(classify, count + data.getCount());
                } else {
                    classifyList.add(classify);
                    moduleData.put(classify, data.getCount());
                }
            } else {
                moduleData = Maps.newLinkedHashMap();
                classifyList.add(classify);
                moduleData.put(classify, data.getCount());
                moduleAndTimeClassifyAndCount.put(data.getModule(), moduleData);
            }
        });
        Set<String> sortedMonth = classifyList.stream().sorted(supplier.getClassifyComparator()).collect(Collectors.toCollection(LinkedHashSet::new));
        List<String> headers = supplier.getHeaders();
        for (Map.Entry<String, Map<String, Integer>> entry : moduleAndTimeClassifyAndCount.entrySet()) {
            String module = entry.getKey();
            Map<String, Integer> qAndCount = entry.getValue();
            List<String> moduleHeaders = Lists.newArrayList(headers);
            moduleHeaders.add(module);
            List<List> charData = Lists.newArrayList();
            charData.add(headers);

            for (String q : sortedMonth) {
                String year = Splitter.on("-").splitToList(q).get(0);
                if (onlyCurrentYear && !year.equals(currentYear + "")) {
                    continue;
                }
                List row = Lists.newArrayList(supplier.getFirstColumnValue(year, Splitter.on("-").splitToList(q).get(1)));
                row.add(qAndCount.get(q));
                charData.add(row);
            }

            charData = useScopeFilter(supplier, charData);
            List<ChartUtils.ChartConfig> typeAndOptions = Lists.newArrayList(new ChartUtils.ChartConfig(ChartUtils.ChartType.ColumnChart)
                    .put("title", supplier.getTitle(module))
                    .put("legend", "left")
                    .put("curveType", "function"));

            ChartUtils.renderChainRatio(supplier.getTitle(module), charData, typeAndOptions, StandardOpenOption.APPEND);
        }
    }

    /**
     * 环比
     */
    @SneakyThrows
    protected void exportChainRatio(ChartDataSupplier supplier) {
        List<List> charData = Lists.newArrayList();
        charData.add(supplier.getHeaders());
        Set<String> timeClassifyList = Sets.newHashSet();

        Map<String, Integer> classifyAndCount = Maps.newHashMap();
        scanData(data -> {
            String classify = supplier.getClassify(data);
            Integer count = classifyAndCount.get(classify);
            if (Objects.nonNull(count)) {
                classifyAndCount.put(classify, count + data.getCount());
            } else {
                timeClassifyList.add(classify);
                classifyAndCount.put(classify, data.getCount());
            }
        });
        Set<String> sortTime = timeClassifyList.stream().sorted(supplier.getClassifyComparator()).collect(Collectors.toCollection(LinkedHashSet::new));
        sortTime.stream().map(q -> Lists.newArrayList(q, classifyAndCount.get(q))).forEach(charData::add);
        List<ChartUtils.ChartConfig> typeAndOptions = Lists.newArrayList();
        typeAndOptions.add(new ChartUtils.ChartConfig(ChartUtils.ChartType.ColumnChart)
                .put("title", supplier.getTitle())
                .put("legend", "left")
                .put("curveType", "function")
        );
        charData = useScopeFilter(supplier, charData);
        ChartUtils.renderChainRatio(supplier.getTitle(), charData, typeAndOptions, StandardOpenOption.APPEND);
    }

    /**
     * 按季度同比
     */
    @SneakyThrows
    protected void exportByModuleAndTimeTongDiff(Integer currentTime, ChartDataSupplier supplier) {
        Integer currentYear = getCurrentYear();
        Set<String> seasonList = Sets.newHashSet();
        Map<String, Map<String, Integer>> moduleAndTimeAndCount = Maps.newHashMap();
        scanData(data -> {
            Map<String, Integer> moduleData = moduleAndTimeAndCount.get(data.getModule());
            String classify = supplier.getClassify(data);
            if (Objects.nonNull(moduleData)) {
                Integer count = moduleData.get(classify);
                if (Objects.nonNull(count)) {
                    moduleData.put(classify, count + data.getCount());
                } else {
                    seasonList.add(classify);
                    moduleData.put(classify, data.getCount());
                }
            } else {
                moduleData = Maps.newLinkedHashMap();
                seasonList.add(classify);
                moduleData.put(classify, data.getCount());
                moduleAndTimeAndCount.put(data.getModule(), moduleData);
            }
        });
        Set<String> sortedSeason = seasonList.stream().sorted(supplier.getClassifyComparator()).collect(Collectors.toCollection(LinkedHashSet::new));
        List baseHeaders = supplier.getHeaders();
        moduleAndTimeAndCount.keySet().forEach(module -> {
            List<List> oldCharData = Lists.newArrayList();
            List<String> headers = Lists.newArrayList(baseHeaders);
            headers.add(module);
            oldCharData.add(headers);
            List<List> newCharData = Lists.newArrayList();
            newCharData.add(headers);
            Map<String, Integer> timeAndCount = moduleAndTimeAndCount.get(module);
            for (String yearAndSeason : sortedSeason) {
                String year = Splitter.on("-").splitToList(yearAndSeason).get(0);
                String classifyTime = Splitter.on("-").splitToList(yearAndSeason).get(1);
                if (Integer.parseInt(classifyTime) >= currentTime) {
                    continue;
                }
                List row = Lists.newArrayList(supplier.getFirstColumnValue(year, classifyTime), Objects.isNull(timeAndCount.get(yearAndSeason)) ? 0 : timeAndCount.get(yearAndSeason));
                if (year.equals(currentYear + "")) {
                    newCharData.add(row);
                } else {
                    oldCharData.add(row);
                }
            }
            List<ChartUtils.ChartConfig> typeAndOptions = new ArrayList();
            typeAndOptions.add(new ChartUtils.ChartConfig(ChartUtils.ChartType.BarChart)
                    .put("title", supplier.getTitle(module)).put("width", "100%", "height", "200px")
                    .put("vAxis", ImmutableMap.of("title", "季度")).put("legend", "top")
                    .put("isStacked", true).diffOption("diff", ImmutableMap.of("oldData", ImmutableMap.of("opacity", 1.0)))
                    .diffOption("title", supplier.getTitle(module) + "同比情况").diffOption("legend", "top")
            );
            oldCharData = useScopeFilter(supplier, oldCharData);
            newCharData = useScopeFilter(supplier, newCharData);
            ChartUtils.renderDiff(supplier.getTitle(module), oldCharData, newCharData, typeAndOptions, StandardOpenOption.APPEND);
        });
    }


    /**
     * 整体 季度环比
     */
    @SneakyThrows
    protected void exportByTongDiff(Integer currentTime, ChartDataSupplier supplier) {
        Integer currentYear = getCurrentYear();

        Set<String> qList = Sets.newHashSet();
        Map<String, Integer> classifyAndCount = Maps.newHashMap();
        scanData(data -> {
            String classify = supplier.getClassify(data);
            Integer count = classifyAndCount.get(classify);
            if (Objects.nonNull(count)) {
                classifyAndCount.put(classify, count + data.getCount());
            } else {
                qList.add(supplier.getClassify(data));
                classifyAndCount.put(classify, data.getCount());
            }
        });

        Set<String> sortedClassify = qList.stream().sorted(supplier.getClassifyComparator())
                .collect(Collectors.toCollection(LinkedHashSet::new));
        List<List> oldCharData = Lists.newArrayList();
        List<String> headers = supplier.getHeaders();
        oldCharData.add(headers);
        List<List> newCharData = Lists.newArrayList();
        newCharData.add(headers);
        for (String classify : sortedClassify) {
            String season = Splitter.on("-").splitToList(classify).get(1);
            if (Integer.parseInt(season) >= currentTime) {
                continue;
            }
            String year = Splitter.on("-").splitToList(classify).get(0);
            if (year.equals(currentYear + "")) {
                newCharData.add(Lists.newArrayList(supplier.getFirstColumnValue(year, season), classifyAndCount.get(classify)));
            } else {
                oldCharData.add(Lists.newArrayList(supplier.getFirstColumnValue(year, season), classifyAndCount.get(classify)));
            }
        }

        List<ChartUtils.ChartConfig> typeAndOptions = Lists.newArrayList();
        typeAndOptions.add(new ChartUtils.ChartConfig(ChartUtils.ChartType.BarChart)
                .put("title", supplier.getTitle())
                .put("legend", "left")
                .put("curveType", "function").diffOption("diff", ImmutableMap.of("oldData", ImmutableMap.of("opacity", 1.0)))
                .diffOption("title", supplier.getTitle()).diffOption("legend", "top")
        );
        oldCharData = useScopeFilter(supplier, oldCharData);
        newCharData = useScopeFilter(supplier, newCharData);
        ChartUtils.renderDiff(supplier.getTitle(), oldCharData, newCharData, typeAndOptions, StandardOpenOption.APPEND);
    }

    private List<List> useScopeFilter(ChartDataSupplier supplier, List<List> chartData) {
        if (chartData.size() - 1 > supplier.getRecordCount()) {
            List header = chartData.get(0);
            chartData = chartData.subList(chartData.size() - supplier.getRecordCount() - 1, chartData.size() - 1);
            chartData.add(0, header);
        }
        return chartData;
    }

    @Data
    protected class DataModule {
        String date;
        String yearAndMonth;
        String yearAndWeek;
        String yearAndQ;
        String module;
        Integer count;

        public DataModule(String date, String module, Integer count) {
            this.date = date;
            this.module = module;
            this.count = count;
            List<String> dateStrArray = Splitter.on("-").splitToList(date);
            this.yearAndMonth = dateStrArray.get(0) + "-" + dateStrArray.get(1);
            this.yearAndQ = dateStrArray.get(0) + "-" + (int) Math.ceil(Integer.parseInt(dateStrArray.get(1)) / 3.0);
            this.yearAndWeek = dateStrArray.get(0) + "-" + getWeek(dateStrArray.get(0), dateStrArray.get(1), dateStrArray.get(2));

        }

        private String getWeek(String year, String month, String date) {
            Integer week = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(date))
                    .get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
            if (week < 10) {
                return "0" + week;
            }
            return "" + week;
        }
    }

    abstract static class ChartDataSupplier {
        abstract String getClassify(DataModule data);

        abstract Comparator<? super String> getClassifyComparator();

        abstract List getHeaders();

        abstract String getTitle(String param);

        String getTitle() {
            return this.getTitle("");
        }

        public String getFirstColumnValue(String year, String classifyTime) {
            return year + "-" + classifyTime;
        }

        /**
         * 多少周，多少月，多少季度
         */
        public Integer getRecordCount() {
            return 12;
        }
    }
}
