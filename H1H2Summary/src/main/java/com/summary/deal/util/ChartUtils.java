package com.summary.deal.util;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wansong
 * @date 2023/7/5
 * @apiNote
 **/
@UtilityClass
public class ChartUtils {

    public enum ChartType {
        ScatterChart, LineChart, Table, PieChart, ComboChart, AreaChart, Bar, SteppedAreaChart,ColumnChart,BarChart
    }

    Gson gson = new GsonBuilder().create();
    /**
     *
     * typeAndOptions.add(new ChartUtils.TypeAndOption(ChartUtils.ChartType.ComboChart)
              .put("title", "按季度/模块异常日志情况")
              .put("vAxis", ImmutableMap.of("title", "模块"), "hAxis", ImmutableMap.of("title", "季度"))
              .put("seriesType", "bars")
              .put("series", ImmutableMap.of(5,ImmutableMap.of("type", "line")))
              .put("width", "100%", "height", "200px")
      );
     **/
    @SneakyThrows
    public void render(String title,List<List> datas, List<TypeAndOption> chartTypeAndOptions) {
        render(title,datas,chartTypeAndOptions,null);
    }

    @SneakyThrows
    public void render(String title,List<List> datas, List<TypeAndOption> chartTypeAndOptions,OpenOption writeOption) {
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
    public void renderChainRatio(String title,List<List> datas, List<TypeAndOption> chartTypeAndOptions) {
        render(title,datas,chartTypeAndOptions,null);
    }

    @SneakyThrows
    public void renderChainRatio(String title,List<List> datas, List<TypeAndOption> chartTypeAndOptions,OpenOption writeOption) {
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
    public void renderDiff(String title,List<List> oldData ,List<List> newData, List<TypeAndOption> chartTypeAndOptions, OpenOption writeOption) {
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
    public void renderDiff(String title,List<List> oldData , List<List> newData, List<TypeAndOption> chartTypeAndOptions) {
        renderDiff(title,oldData,newData,chartTypeAndOptions,null);
    }
    @Data
    public static class TypeAndOption {
        ChartType type;
        Map<Object, Object> options;
        Map<Object, Object> diffOptions;

        public TypeAndOption(ChartType type) {
            this.type = type;
        }

        public TypeAndOption put(String key, Object value) {
            getOptions().put(key, value);
            return this;
        }
        public TypeAndOption put(String key, Object value,String key2, Object value2) {
            getOptions().put(key, value);
            getOptions().put(key2, value2);
            return this;
        }

        public TypeAndOption diffOption(String key, Object value) {
            getDiffOptions().put(key, value);
            return this;
        }
        public TypeAndOption diffOption(String key, Object value,String key2, Object value2) {
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
