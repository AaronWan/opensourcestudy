package com.study.elasticsearch.script;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpHost;
import org.apache.lucene.index.Terms;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/12
 * @creat_time: 16:52
 * @since 7.2.0
 */
public class ElasticSearchImport {

  public static void main(String[] args) throws Exception {
    ElasticSearchImport importES = new ElasticSearchImport();
    importES.init();
    importData(importES);
    importES.client.close();
  }
  public RestHighLevelClient client;

  public void init() {
    client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")));
  }

  static Gson gson = new GsonBuilder().create();

  public void execute(String indexName, String id, Object data) {
    // 1、创建索引请求
    IndexRequest request = new IndexRequest(indexName,   //索引
      "_doc", id);     //文档id

    // 2、准备文档数据
    // 方式一：直接给JSON串
    String jsonString = gson.toJson(data);
    request.source(jsonString, XContentType.JSON);
    //4、发送请求
    IndexResponse indexResponse = null;
    try {
      // 同步方式
      RequestOptions options = RequestOptions.DEFAULT;
      indexResponse = client.index(request, options);
    } catch (ElasticsearchException | IOException e) {
      // 捕获，并处理异常
      //判断是否版本冲突、create但文档已存在冲突
    }

    //5、处理响应
    if (indexResponse != null) {
      if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
      } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
        System.out.println("修改文档成功，处理逻辑代码写到这里。");
      }
      // 分片处理信息
      ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
      if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

      }
      // 如果有分片副本失败，可以获得失败原因信息
      if (shardInfo.getFailed() > 0) {
        for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
          String reason = failure.reason();
          System.out.println("副本失败原因：" + reason);
        }
      }
    }
  }


  private static void export(ElasticSearchImport importES, String fileName, BiConsumer<ElasticSearchImport, String> consume) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ScriptTest.class.getResource("/" + fileName).getPath())));
    String line;
    while ((line = br.readLine()) != null) {
      consume.accept(importES, line);
    }
  }

  private static BiConsumer<ElasticSearchImport, String> exportQuartz() throws IOException {
    BiConsumer<ElasticSearchImport, String> consume = (es, line) -> {
      Map<String, Object> data = Maps.newHashMap();
      String[] temp = line.replaceAll(" ", "").replaceAll("\"","").split("tenantId|type");
      try {
        data.put("tenantId", temp[1]);
        data.put("count", temp[0]);
        data.put("table", "quartz");
        es.execute("engine_use_data", temp[1] + "-" +"quartz", data);
      } catch (Exception e) {
        System.out.println(line);
      }
    };
    return consume;
  }
  private static BiConsumer<ElasticSearchImport, String> exportWorkflows() throws IOException {
    BiConsumer<ElasticSearchImport, String> consume = (es, line) -> {
      Map<String, Object> data = Maps.newHashMap();
      String[] temp = line.replaceAll(" ", "").replaceAll("\"","").split("tenantId|type");
      try {
        data.put("tenantId", temp[1]);
        data.put("count", temp[0]);
        data.put("type", temp[2]);
        data.put("table", "workflows");
        es.execute("engine_use_data", temp[1] + "-" + temp[2], data);
      } catch (Exception e) {
        System.out.println(line);
      }
    };
    return consume;
  }
  private static BiConsumer<ElasticSearchImport, String> exportWorkflowTasks() throws IOException {
    BiConsumer<ElasticSearchImport, String> consume = (es, line) -> {
      Map<String, Object> data = Maps.newHashMap();
      String[] temp = line.replaceAll(" ", "").split("tenantId|type");
      try {
        data.put("tenantId", temp[1]);
        data.put("count", temp[0]);
        data.put("type", temp[2]);
        data.put("table", "workflowTasks");
      } catch (Exception e) {
        System.out.println(line);
      }
      es.execute("engine_use_data", temp[1] + "-" + temp[2], data);
    };
    return consume;
  }

  private static BiConsumer<ElasticSearchImport, String> exportWorkflowInstances() throws IOException {
    BiConsumer<ElasticSearchImport, String> consume = (es, line) -> {
      Map<String, Object> data = Maps.newHashMap();
      String[] temp = line.replaceAll(" ", "").split("tenantId|type");
      try {
        data.put("tenantId", temp[1]);
        data.put("count", temp[0]);
        data.put("type", temp[2]);
        data.put("table", "workflowInstances");
      } catch (Exception e) {
        System.out.println(line);
      }
      es.execute("engine_use_data", temp[1] + "-" + temp[2], data);
    };
    return consume;
  }

  private static BiConsumer<ElasticSearchImport, String> exportTasks() throws IOException {
    BiConsumer<ElasticSearchImport, String> consume = (es, line) -> {
      Map<String, Object> data = Maps.newHashMap();
      String[] temp = line.replaceAll(" ", "").split("tenantId|type");
      try {
        data.put("tenantId", temp[1]);
        data.put("count", temp[0]);
        data.put("type", temp[2]);
        data.put("table", "tasks");
        es.execute("engine_use_data", temp[1] + "-" + temp[2], data);
      } catch (Exception e) {
        System.out.println(line);
      }
    };
    return consume;
  }

  private static void importData(ElasticSearchImport importES)throws Exception{
    export(importES, "tasks.csv", exportTasks());
//    export(importES, "workflow_quartz.csv.result", exportQuartz());
//    export(importES, "workflow_tasks.csv.result", exportWorkflowTasks());
//    export(importES, "workflowInstances.csv.result", exportWorkflowInstances());
//    export(importES, "workflows.csv.result", exportWorkflows());
  }
}
