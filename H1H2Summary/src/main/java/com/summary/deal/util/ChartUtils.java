package com.summary.deal.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wansong
 * @date 2023/7/5
 * @apiNote
 **/
@UtilityClass
public class ChartUtils {
    /**
     * ChainRatioChart 是环比的一个自定义类型图表， 会特殊处理数据成 同比
     * 只有 TrendChainRatio.vm 支持
     */
    public enum ChartType {
        ScatterChart, LineChart, Table, PieChart, ComboChart, AreaChart, Bar, SteppedAreaChart,ColumnChart,BarChart,ChainRatioChart
    }

    static Gson gson = new GsonBuilder().create();
    /**
     *
     * typeAndOptions.add(new ChartUtils.ChartConfig(ChartUtils.ChartType.ComboChart)
              .put("title", "按季度/模块异常日志情况")
              .put("vAxis", ImmutableMap.of("title", "模块"), "hAxis", ImmutableMap.of("title", "季度"))
              .put("seriesType", "bars")
              .put("series", ImmutableMap.of(5,ImmutableMap.of("type", "line")))
              .put("width", "100%", "height", "200px")
      );
     **/
    @SneakyThrows
    public void render(String title,List<List> datas, List<ChartConfig> chartTypeAndOptions) {
        render(title,datas,chartTypeAndOptions,null);
    }

    @SneakyThrows
    public void render(String title, List<List> datas, List<ChartConfig> chartTypeAndOptions, OpenOption writeOption) {
        if(!Paths.get("chart.html").toFile().exists()){
            Paths.get("chart.html").toFile().createNewFile();
        }
        Map bindings = Maps.newHashMap();
        bindings.put("id", System.currentTimeMillis());
        bindings.put("typeAndOptions", chartTypeAndOptions.stream().collect(Collectors.toMap(typeAndOption -> typeAndOption.getType(), typeAndOption -> gson.toJson(typeAndOption.options),(a,b)->b, LinkedHashMap::new)));
        bindings.put("rows", gson.toJson(datas));
        bindings.put("title", title);
        if(Objects.nonNull(writeOption)){
            Files.write(Paths.get("chart.html"), TemplateViewUtil.renderHtml("Template.vm", bindings).getBytes(),writeOption);
        }else{
            Files.write(Paths.get("chart.html"), TemplateViewUtil.renderHtml("Template.vm", bindings).getBytes());
        }
    }

    @SneakyThrows
    public void renderChainRatio(String title,List<List> datas, List<ChartConfig> chartTypeAndOptions) {
        render(title,datas,chartTypeAndOptions,null);
    }

    @SneakyThrows
    public void renderOnlyChainRatio(List<List<ChartUtils.ChartDivConfig>> chartDivConfigs) {
        renderOnlyChainRatio(chartDivConfigs,null);
    }

    @SneakyThrows
    public void renderOnlyChainRatio(List<List<ChartUtils.ChartDivConfig>> chartDivConfigs, OpenOption writeOption) {
        if(!Paths.get("chart.html").toFile().exists()){
            Paths.get("chart.html").toFile().createNewFile();
        }
        Map<String,Object> binds= gson.fromJson(gson.toJson(ImmutableMap.of("data",chartDivConfigs)),LinkedHashMap.class);
        if(Objects.nonNull(writeOption)){
            Files.write(Paths.get("chart.html"), TemplateViewUtil.renderHtml("TrendChainRatio.vm", binds).getBytes(),writeOption);
        }else{
            Files.write(Paths.get("chart.html"), TemplateViewUtil.renderHtml("TrendChainRatio.vm", binds).getBytes());
        }
    }

    @SneakyThrows
    public void renderChainRatio(String title, List<List> datas, List<ChartConfig> chartTypeAndOptions, OpenOption writeOption) {
        if(!Paths.get("chart.html").toFile().exists()){
            Paths.get("chart.html").toFile().createNewFile();
        }
        Map bindings = Maps.newHashMap();
        bindings.put("id", System.currentTimeMillis());
        bindings.put("typeAndOptions", chartTypeAndOptions.stream().collect(Collectors.toMap(typeAndOption -> typeAndOption.getType(), typeAndOption -> gson.toJson(typeAndOption.options),(a,b)->b, LinkedHashMap::new)));
        bindings.put("rows", gson.toJson(datas));
        bindings.put("title", title);
        if(Objects.nonNull(writeOption)){
            Files.write(Paths.get("chart.html"), TemplateViewUtil.renderHtml("ChainRatio.vm", bindings).getBytes(),writeOption);
        }else{
            Files.write(Paths.get("chart.html"), TemplateViewUtil.renderHtml("ChainRatio.vm", bindings).getBytes());
        }
    }

    @SneakyThrows
    public void renderDiff(String title, List<List> oldData , List<List> newData, List<ChartConfig> chartTypeAndOptions, OpenOption writeOption) {
        if(!Paths.get("chart.html").toFile().exists()){
            Paths.get("chart.html").toFile().createNewFile();
        }
        Map bindings = Maps.newHashMap();
        bindings.put("id", System.currentTimeMillis());
        bindings.put("title", title);
        bindings.put("typeAndOptions", chartTypeAndOptions.stream().collect(Collectors.toMap(typeAndOption -> typeAndOption.getType(), typeAndOption -> {
            Map<String,String> options=Maps.newHashMap();
            options.put("options", gson.toJson(typeAndOption.options));
            options.put("diffOptions", gson.toJson(typeAndOption.diffOptions));
            return options;
        },(a, b)->b, LinkedHashMap::new)));
        bindings.put("oldRows", gson.toJson(oldData));
        bindings.put("newRows", gson.toJson(newData));
        if(Objects.nonNull(writeOption)){
            Files.write(Paths.get("chart.html"), TemplateViewUtil.renderHtml("YoY.vm", bindings).getBytes(),writeOption);
        }else{
            Files.write(Paths.get("chart.html"), TemplateViewUtil.renderHtml("YoY.vm", bindings).getBytes());
        }
    }
    @SneakyThrows
    public void renderDiff(String title,List<List> oldData , List<List> newData, List<ChartConfig> chartTypeAndOptions) {
        renderDiff(title,oldData,newData,chartTypeAndOptions,null);
    }
    @Data
    public static class ChartDivConfig{
        String id= UUID.randomUUID().toString().replaceAll("-", "");
        String title;
        String data;
        ChartType type;
        String options;
        String diffOptions;

        public ChartDivConfig(String title, ChartConfig config,List<List> data) {
            this.title = title;
            this.type = config.getType();
            this.options=gson.toJson(config.getOptions());
            this.diffOptions=gson.toJson(config.getDiffOptions());
            this.data=gson.toJson(data);
        }
    }
    @Data
    public static class ChartConfig {
        ChartType type;
        Map<Object, Object> options;
        Map<Object, Object> diffOptions;

        public ChartConfig(ChartType type) {
            this.type = type;
        }

        public ChartConfig put(String key, Object value) {
            getOptions().put(key, value);
            return this;
        }
        public ChartConfig put(String key, Object value, String key2, Object value2) {
            getOptions().put(key, value);
            getOptions().put(key2, value2);
            return this;
        }

        public ChartConfig diffOption(String key, Object value) {
            getDiffOptions().put(key, value);
            return this;
        }
        public ChartConfig diffOption(String key, Object value, String key2, Object value2) {
            getDiffOptions().put(key, value);
            getDiffOptions().put(key2, value2);
            return this;
        }
        public Map<Object, Object> getDiffOptions() {
            if (Objects.isNull(diffOptions)) {
                diffOptions = Maps.newHashMap();
            }
            return diffOptions;
        }
        public Map<Object, Object> getOptions() {
            if (Objects.isNull(options)) {
                options = Maps.newHashMap();
            }
            return options;
        }
    }
}
