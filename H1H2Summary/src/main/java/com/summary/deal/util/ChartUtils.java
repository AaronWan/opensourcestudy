package com.summary.deal.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.summary.deal.util.chart.model.ChartConfig;
import com.summary.deal.util.chart.model.ChartDivConfigTheme;
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
     *
     * typeAndOptions.add(new ChartConfig(ChartType.ComboChart)
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
        bindings.put("typeAndOptions", chartTypeAndOptions.stream().collect(Collectors.toMap(typeAndOption -> typeAndOption.getType(), typeAndOption -> GsonUtil.toJson(typeAndOption.getOptions()),(a,b)->b, LinkedHashMap::new)));
        bindings.put("rows", GsonUtil.toJson(datas));
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
    public void renderTheme(ChartDivConfigTheme theme) {
        renderTheme(theme,null);
    }

    @SneakyThrows
    public void renderTheme(ChartDivConfigTheme chartDivConfigTheme, OpenOption writeOption) {
        if(!Paths.get("chart.html").toFile().exists()){
            Paths.get("chart.html").toFile().createNewFile();
        }
        Map<String,Object> binds= GsonUtil.fromJson(GsonUtil.toJson(ImmutableMap.of("data",chartDivConfigTheme)),LinkedHashMap.class);
        if(Objects.nonNull(writeOption)){
            Files.write(Paths.get("chart.html"), TemplateViewUtil.renderHtml("Theme.vm", binds).getBytes(),writeOption);
        }else{
            Files.write(Paths.get("chart.html"), TemplateViewUtil.renderHtml("Theme.vm", binds).getBytes());
        }
    }

    @SneakyThrows
    public void renderChainRatio(String title, List<List> datas, List<ChartConfig> chartTypeAndOptions, OpenOption writeOption) {
        if(!Paths.get("chart.html").toFile().exists()){
            Paths.get("chart.html").toFile().createNewFile();
        }
        Map bindings = Maps.newHashMap();
        bindings.put("id", System.currentTimeMillis());
        bindings.put("typeAndOptions", chartTypeAndOptions.stream().collect(Collectors.toMap(typeAndOption -> typeAndOption.getType(), typeAndOption -> GsonUtil.toJson(typeAndOption.getOptions()),(a,b)->b, LinkedHashMap::new)));
        bindings.put("rows", GsonUtil.toJson(datas));
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
            options.put("options", GsonUtil.toJson(typeAndOption.getOptions()));
            options.put("diffOptions", GsonUtil.toJson(typeAndOption.getDiffOptions()));
            return options;
        },(a, b)->b, LinkedHashMap::new)));
        bindings.put("oldRows", GsonUtil.toJson(oldData));
        bindings.put("newRows", GsonUtil.toJson(newData));
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

}
