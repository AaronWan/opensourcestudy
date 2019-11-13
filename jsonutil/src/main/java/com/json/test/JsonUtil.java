package com.json.test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.gson.*;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 万松(Aaron)
 * @since 6.2
 */
public class JsonUtil {
    @Test
    public void fastJsonTest() {
        String jsonStr = "{\"remindLatency\":10800000,\"assignees\":{\"ext_bpm\":[\"activity_1515569906241##LeadsObj##owner$$数据相关人员-数据负责人\"]},\"objectIds\":[\"dce08b110b244e16aaea897e670ee6c3\"],\"duration\":4392188138,\"sourceWorkflowId\":\"320442637622247424\",\"activityId\":\"1515569906241\",\"stageName\":\"销售主管阶段\",\"startTime\":1517911547514,\"state\":\"pass\",\"workflowInstanceId\":\"5a72ad594fa424c1186e3583\",\"stageId\":\"1515569906240\",\"owner\":[\"1003\"],\"record_type\":\"default__c\",\"relevant_team\":[{\"teamMemberEmployee\":[\"1003\"],\"teamMemberPermissionType\":\"1\",\"teamMemberRole\":\"4\"}],\"timeoutTime\":4381388138,\"processorIds\":[\"1003\"],\"isTimeout\":true,\"name\":\"主管信息补充(2018-02-06 18:05)\",\"objectApiName\":\"LeadsObj\",\"taskName\":\"主管信息补充\",\"_id\":\"5a797dfb4fa424c11872f783\",\"endTime\":1522303735652,\"workflowInstanceName\":\"线索分配流程(2018-02-01 14:02)\",\"objectDataId\":\"dce08b110b244e16aaea897e670ee6c3\",\"workflowId\":\"5a55c7024fa424457e06c266\"}";
        HashMap obj = JSON.parseObject(jsonStr, HashMap.class);
        System.out.println(obj);
    }

    @Test
    public void dataDetail() {
        String data = FileUtils.toString("/data.txt");
        List<String> rst = Splitter.on("\n").splitToList(data);
        AtomicInteger count = new AtomicInteger();
        AtomicInteger tcount = new AtomicInteger();
        rst.forEach(item -> {
            HashMap temp = gson.fromJson(item, HashMap.class);
            if (temp != null) {
                tcount.incrementAndGet();
                int size = Splitter.on(",").splitToList(((List) temp.get("instanceIds")).get(0) + "").size();
                count.addAndGet(size);
                System.out.println(temp.get("tenant_id") + ":" + size);
            }
        });
        System.out.println("tenants:"+tcount.get()+"\t error_data_size:"+count.get());

    }

    public static Gson gson = new GsonBuilder().registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
        BigDecimal value = BigDecimal.valueOf(src);

        return new JsonPrimitive(value);
    }).create();


    public static void main(String[] args) {
        List<String> list = Splitter.on(",").splitToList("5ac744b0cc9da333db2ec991,5ac818624fa42489c679ddbb,5ac6cb41cc9da333db2e8305,5ac6dbe64fa42489c6797195,5ac6dc814fa42489c67971f1,5ac6dc8dcc9da333db2e8f3a,5ac6dcdacc9da333db2e8f95,5ac6dd13cc9da333db2e8fb6,5ac6dd4d4fa42489c6797298,5ac6ddb04fa42489c67972dc,5ac6de65cc9da333db2e9092,5ac6df904fa42489c6797416,5ac6dfbecc9da333db2e9175,5ac6dfcd4fa42489c6797447,5ac6dffacc9da333db2e9199,5ac6e0b14fa42489c6797504,5ac6e0cd4fa42489c679751b,5ac6e0d3cc9da333db2e9223,5ac6e178cc9da333db2e92fa,5ac6e194cc9da333db2e9315,5ac6e1a04fa42489c67975f3,5ac6e1f5cc9da333db2e9359,5ac6e213cc9da333db2e9373,5ac6e218cc9da333db2e937d,5ac6e26d4fa42489c6797665,5ac6e2a5cc9da333db2e93b8,5ac6e33ccc9da333db2e9402,5ac6e342cc9da333db2e9406,5ac6e4004fa42489c6797723,5ac6e48c4fa42489c679777a,5ac6e4e24fa42489c67977b4,5ac6e502cc9da333db2e9509,5ac6e5104fa42489c67977d0,5ac6e5d44fa42489c679783d,5ac6e65fcc9da333db2e95e0,5ac6e6804fa42489c67978b9,5ac6e6eb4fa42489c6797902,5ac6e77dcc9da333db2e96ce,5ac6e79ecc9da333db2e96ea,5ac6e888cc9da333db2e97c0,5ac6e88b4fa42489c6797a4a,5ac6e8fdcc9da333db2e984c,5ac6e905cc9da333db2e9857,5ac6e98acc9da333db2e990f,5ac6e9b3cc9da333db2e9941,5ac6ea20cc9da333db2e9991,5ac6ea224fa42489c6797ba8,5ac6ea27cc9da333db2e9998,5ac6eb2a4fa42489c6797c6e,5ac6eb89cc9da333db2e9aa3,5ac6ebb44fa42489c6797cd3,5ac6ec0ccc9da333db2e9aed,5ac6ece7cc9da333db2e9b85,5ac6ed714fa42489c6797de3,5ac6edc84fa42489c6797e13,5ac6edd14fa42489c6797e19,5ac6ee274fa42489c6797e53,5ac6eef64fa42489c6797eed,5ac6ef8b4fa42489c6797f47,5ac6f039cc9da333db2e9d27,5ac6f05bcc9da333db2e9d3a,5ac6f2e4cc9da333db2e9e60,5ac6f60c4fa42489c67981d8,5ac6f9d34fa42489c6798302,5ac6fd774fa42489c679849f,5ac6fe4dcc9da333db2ea287,5ac6ff71cc9da333db2ea307,5ac70076cc9da333db2ea35c,5ac700ac4fa42489c67985f4,5ac703424fa42489c67986ec,5ac704304fa42489c6798784,5ac7063bcc9da333db2ea5de,5ac7076d4fa42489c679889b,5ac70784cc9da333db2ea64f,5ac7082b4fa42489c67988fe,5ac708a3cc9da333db2ea6d7,5ac70c674fa42489c6798b46,5ac70d82cc9da333db2ea9a3,5ac70dc74fa42489c6798bf1,5ac70f134fa42489c6798c5c,5ac71029cc9da333db2eaaa0,5ac710c7cc9da333db2eaaf1,5ac712f4cc9da333db2eac4e,5ac7145ecc9da333db2eacf0,5ac714a0cc9da333db2ead17,5ac714a34fa42489c6798f44,5ac71645cc9da333db2eada2,5ac716814fa42489c6799021,5ac716a24fa42489c6799037,5ac716ab4fa42489c679903d,5ac716ba4fa42489c679904b,5ac71733cc9da333db2eae31,5ac717764fa42489c67990c4,5ac718c24fa42489c6799175,5ac71920cc9da333db2eaf06,5ac7195ecc9da333db2eaf24,5ac719794fa42489c67991c6,5ac71988cc9da333db2eaf29,5ac71a304fa42489c6799206,5ac71dad4fa42489c679939f,5ac71dc74fa42489c67993b6,5ac71dd5cc9da333db2eb111,5ac71eaf4fa42489c6799434,5ac71eb94fa42489c6799437,5ac71ec94fa42489c6799440,5ac71ee74fa42489c679944f,5ac71f0bcc9da333db2eb1b8,5ac71f75cc9da333db2eb1e5,5ac72004cc9da333db2eb22e,5ac7207bcc9da333db2eb25f,5ac7207f4fa42489c67994fb,5ac720d94fa42489c679955a,5ac720dc4fa42489c679955d,5ac721054fa42489c6799574,5ac72110cc9da333db2eb2c2,5ac7213d4fa42489c67995a8,5ac721944fa42489c67995c9,5ac721984fa42489c67995d0,5ac721bccc9da333db2eb30f,5ac7223acc9da333db2eb35e,5ac7223ccc9da333db2eb364,5ac722464fa42489c6799654,5ac7224a4fa42489c679965f,5ac7224dcc9da333db2eb377,5ac72306cc9da333db2eb41e,5ac723094fa42489c679971f,5ac723814fa42489c679978a,5ac72449cc9da333db2eb50f,5ac724504fa42489c67997e0,5ac724fe4fa42489c6799838,5ac7257fcc9da333db2eb599,5ac725864fa42489c6799898,5ac725dbcc9da333db2eb5e0,5ac726394fa42489c67998ef,5ac72690cc9da333db2eb625,5ac72864cc9da333db2eb750,5ac728d84fa42489c6799a83,5ac729e8cc9da333db2eb83e,5ac729f54fa42489c6799b07,5ac72a5ecc9da333db2eb87c,5ac72a934fa42489c6799b5f,5ac72b984fa42489c6799c09,5ac72ba3cc9da333db2eb967,5ac72bcfcc9da333db2eb992,5ac72c65cc9da333db2eba06,5ac72c6ccc9da333db2eba09,5ac72c824fa42489c6799cca,5ac72c89cc9da333db2eba2c,5ac72ce5cc9da333db2eba6e,5ac72d3ccc9da333db2ebaa4,5ac72d514fa42489c6799d71,5ac72d794fa42489c6799d8a,5ac72dbe4fa42489c6799dbb,5ac72e364fa42489c6799df9,5ac72e6c4fa42489c6799e2c,5ac72fcacc9da333db2ebbe0,5ac730574fa42489c6799f1b,5ac7305dcc9da333db2ebc1e,5ac7307ccc9da333db2ebc3c,5ac7309ecc9da333db2ebc51,5ac730b6cc9da333db2ebc5c,5ac730eccc9da333db2ebc7a,5ac73113cc9da333db2ebc87,5ac7313ccc9da333db2ebcad,5ac7315f4fa42489c6799fd6,5ac731cf4fa42489c679a017,5ac731e34fa42489c679a029,5ac7320a4fa42489c679a058,5ac732354fa42489c679a08f,5ac7328bcc9da333db2ebd85,5ac7328d4fa42489c679a0c6,5ac7331bcc9da333db2ebdf2,5ac733874fa42489c679a156,5ac733964fa42489c679a15a,5ac733a6cc9da333db2ebe4c,5ac733ba4fa42489c679a17c,5ac733dbcc9da333db2ebe6e,5ac73457cc9da333db2ebec9,5ac7346ecc9da333db2ebed7,5ac734894fa42489c679a1eb,5ac73493cc9da333db2ebef4,5ac734b5cc9da333db2ebf0a,5ac734c1cc9da333db2ebf12,5ac7357f4fa42489c679a282,5ac735cecc9da333db2ebfdb,5ac736484fa42489c679a308,5ac7365e4fa42489c679a30f,5ac736654fa42489c679a315,5ac73685cc9da333db2ec03b,5ac73749cc9da333db2ec0ac,5ac737ba4fa42489c679a3d9,5ac737e6cc9da333db2ec111,5ac73845cc9da333db2ec143,5ac7388a4fa42489c679a458,5ac738cbcc9da333db2ec1ad,5ac738fa4fa42489c679a4b8,5ac738fe4fa42489c679a4ba,5ac739154fa42489c679a4d6,5ac73a164fa42489c679a57f,5ac73c2e4fa42489c679a731,5ac73c5c4fa42489c679a761,5ac73cdb4fa42489c679a7df,5ac73cefcc9da333db2ec58a,5ac73d1d4fa42489c679a80b,5ac73e8f4fa42489c679a906,5ac73eb9cc9da333db2ec6b8,5ac73f54cc9da333db2ec729,5ac74014cc9da333db2ec781,5ac741b1cc9da333db2ec860,5ac742e94fa42489c679ab5c,5ac743174fa42489c679ab81,5ac743e74fa42489c679abda,5ac74463cc9da333db2ec977,5ac7464f4fa42489c679ad0e,5ac746b24fa42489c679ad6d,5ac747574fa42489c679addd,5ac74855cc9da333db2ecb98,5ac74a62cc9da333db2ecc39,5ac74adccc9da333db2ecc62,5ac74b67cc9da333db2eccf2,5ac74bb6cc9da333db2ecd28,5ac74d1a4fa42489c679b124,5ac74d74cc9da333db2ece0b,5ac74ee7cc9da333db2ece7b,5ac75409cc9da333db2ed00c,5ac75615cc9da333db2ed12d,5ac75b524fa42489c679b6f2,5ac76331cc9da333db2ed639,5ac77fb1cc9da333db2ee38c,5ac8065bcc9da333db2ef052,5ac812e5cc9da333db2ef6ba,5ac8165ccc9da333db2efa26,5ac6e9e04fa42489c6797b85,5ac6ea07cc9da333db2e997d,5ac6ee9a4fa42489c6797e9b,5ac80b844fa42489c679d2c3,5ac80c1c4fa42489c679d301,5ac80c244fa42489c679d308,5ac80c5ecc9da333db2ef1be,5ac80c91cc9da333db2ef1cb,5ac80cdf4fa42489c679d369,5ac80ced4fa42489c679d37a,5ac80cf44fa42489c679d381,5ac80d06cc9da333db2ef212,5ac80d31cc9da333db2ef240,5ac80d6b4fa42489c679d406,5ac80d96cc9da333db2ef279,5ac80de8cc9da333db2ef2b2,5ac80e9c4fa42489c679d4fe,5ac8111ccc9da333db2ef55f,5ac811744fa42489c679d74f,5ac811894fa42489c679d75c,5ac812d54fa42489c679d85a,5ac8147b4fa42489c679d9e2,5ac814d44fa42489c679da44,5ac81669cc9da333db2efa41,5ac818304fa42489c679dd87,5ac81859cc9da333db2efc65,5ac819134fa42489c679de66,5ac81ad24fa42489c679e04d,5ac48750cc9da333db2dab6c,5ac6cd0dcc9da333db2e845d,5ac6dbd54fa42489c6797182,5ac6e467cc9da333db2e949a,5ac6fbc04fa42489c67983bc,5ac70396cc9da333db2ea50d,5ac70b33cc9da333db2ea848,5ac70fa0cc9da333db2eaa4e,5ac7117ecc9da333db2eab63,5ac711fe4fa42489c6798dd5,5ac714644fa42489c6798f2b,5ac715284fa42489c6798f8b,5ac7172b4fa42489c6799092,5ac71829cc9da333db2eaea4,5ac7198ecc9da333db2eaf2c,5ac721b34fa42489c67995f0,5ac7264dcc9da333db2eb604,5ac726c0cc9da333db2eb649,5ac728bd4fa42489c6799a74,5ac72babcc9da333db2eb976,5ac72df7cc9da333db2ebb11,5ac737634fa42489c679a3af,5ac73ac94fa42489c679a60e,5ac73bb74fa42489c679a6df,5ac73c76cc9da333db2ec505,5ac73c86cc9da333db2ec51e,5ac73fe9cc9da333db2ec773,5ac7467d4fa42489c679ad33,5ac7472ccc9da333db2ecaf9,5ac74768cc9da333db2ecb1e,5ac747a6cc9da333db2ecb3b,5ac747cc4fa42489c679ae36,5ac749a14fa42489c679af24,5ac74a5c4fa42489c679af77,5ac74ae6cc9da333db2ecc6c,5ac74afacc9da333db2ecc83,5ac74b51cc9da333db2ecce3,5ac74cf1cc9da333db2ecdcf,5ac74f98cc9da333db2ece9e,5ac74fb34fa42489c679b237,5ac7506c4fa42489c679b287,5ac752d0cc9da333db2ecfa9,5ac75507cc9da333db2ed082,5ac75514cc9da333db2ed088,5ac755cf4fa42489c679b4ba,5ac755d44fa42489c679b4bd,5ac757224fa42489c679b54a,5ac757b24fa42489c679b576,5ac7581b4fa42489c679b589,5ac761c14fa42489c679b9e1,5ac76222cc9da333db2ed5ce,5ac7664d4fa42489c679bb7d,5ac766c74fa42489c679bba3,5ac78ad6cc9da333db2ee8e5,5ac80fa7cc9da333db2ef482,5ac810afcc9da333db2ef513,5ac8112f4fa42489c679d72a,5ac811fdcc9da333db2ef5f7,5ac812d4cc9da333db2ef6a9,5ac813674fa42489c679d8ee,5ac81407cc9da333db2ef7ef,5ac81666cc9da333db2efa3a,5ac8170f4fa42489c679dc6f,5ac8172acc9da333db2efb13,5ac818bd4fa42489c679de15,5ac818e74fa42489c679de2e,5ac81917cc9da333db2efd3b,5ac81963cc9da333db2efd88,5ac8196c4fa42489c679ded2,5ac81987cc9da333db2efdbb,5ac819dacc9da333db2efdf6,5ac819fdcc9da333db2efe1b,5ac81a37cc9da333db2efe4c,5ac81abc4fa42489c679e03c,5ac81b064fa42489c679e084,5ac719e8cc9da333db2eaf54,5ac73768cc9da333db2ec0b9,5ac81b694fa42489c679e0dc,5ac81b5fcc9da333db2eff5b,5ac81b3dcc9da333db2eff37,5ac81b10cc9da333db2eff05,5ac818584fa42489c679ddac,5ac81ad7cc9da333db2efed6,5ac81a9dcc9da333db2efeab,5ac81a8f4fa42489c679e011,5ac81a86cc9da333db2efe91,5ac81a114fa42489c679df84,5ac819eacc9da333db2efe0c,5ac819b64fa42489c679df2f,5ac81a18cc9da333db2efe2d,5ac81a244fa42489c679df94,5ac8195d4fa42489c679dec0,5ac81968cc9da333db2efd8a,5ac818aecc9da333db2efcd2,5ac8195d4fa42489c679dec3,5ac817d34fa42489c679dd36,5ac818604fa42489c679ddb6,5ac81881cc9da333db2efca7,5ac817fb4fa42489c679dd4d,5ac814784fa42489c679d9dd,5ac817b14fa42489c679dd24,5ac81700cc9da333db2efaf7,5ac814a4cc9da333db2ef87f,5ac81742cc9da333db2efb2e,5ac816c8cc9da333db2efaa9,5ac816cdcc9da333db2efaad,5ac816a84fa42489c679dbdd,5ac816a0cc9da333db2efa6d,5ac81695cc9da333db2efa65,5ac8165dcc9da333db2efa28,5ac81579cc9da333db2ef963,5ac8149bcc9da333db2ef875,5ac81476cc9da333db2ef853,5ac814b3cc9da333db2ef898,5ac80cfecc9da333db2ef208,5ac813ed4fa42489c679d970,5ac813a04fa42489c679d928,5ac813a54fa42489c679d92d,5ac81390cc9da333db2ef78c,5ac80cd74fa42489c679d362,5ac8138bcc9da333db2ef786,5ac813214fa42489c679d89d,5ac812f74fa42489c679d87c,5ac812f5cc9da333db2ef6c5,5ac81277cc9da333db2ef65f,5ac80a2f4fa42489c679d266,5ac8118ccc9da333db2ef5bb,5ac8123c4fa42489c679d7dd,5ac812274fa42489c679d7c6,5ac811d7cc9da333db2ef5e9,5ac8115ecc9da333db2ef5a7,5ac80f244fa42489c679d5c4,5ac8106e4fa42489c679d680,5ac8103ccc9da333db2ef4c7,5ac8102b4fa42489c679d64e,5ac80f7ecc9da333db2ef46d,5ac80f69cc9da333db2ef466,5ac80f264fa42489c679d5c8,5ac80deccc9da333db2ef2bd,5ac80de84fa42489c679d449,5ac80c154fa42489c679d2fc,5ac80d91cc9da333db2ef275,5ac80d53cc9da333db2ef263,5ac80b274fa42489c679d2ab,5ac80aadcc9da333db2ef0f4,5ac807e54fa42489c679d202,5ac78adfcc9da333db2ee8ec,5ac78ae74fa42489c679cab1,5ac7899a4fa42489c679ca3d,5ac78070cc9da333db2ee437,5ac7079bcc9da333db2ea657,5ac6f225cc9da333db2e9e08,5ac76b99cc9da333db2edab9,5ac769eb4fa42489c679bd4f,5ac769354fa42489c679bcda,5ac767304fa42489c679bbc4,5ac7665f4fa42489c679bb83,5ac763fbcc9da333db2ed67d,5ac763814fa42489c679ba9c,5ac76292cc9da333db2ed60f,5ac7622a4fa42489c679ba13,5ac7614c4fa42489c679b9bb,5ac7611f4fa42489c679b9a4,5ac760c74fa42489c679b97c,5ac76082cc9da333db2ed536,5ac75fd04fa42489c679b916,5ac75f66cc9da333db2ed497,5ac75e894fa42489c679b884,5ac75c674fa42489c679b78a,5ac75bfb4fa42489c679b741,5ac75c09cc9da333db2ed36a,5ac718f64fa42489c679918a,5ac754f0cc9da333db2ed070,5ac755554fa42489c679b479,5ac754f3cc9da333db2ed074,5ac7552ecc9da333db2ed097,5ac7538c4fa42489c679b3a5,5ac7509f4fa42489c679b297,5ac74f3e4fa42489c679b204,5ac74e374fa42489c679b1c0,5ac74d19cc9da333db2ecddf,5ac74c9f4fa42489c679b0c8,5ac74cb14fa42489c679b0d1,5ac74b144fa42489c679afe6,5ac74bd54fa42489c679b044,5ac74b86cc9da333db2ecd0a,5ac73d1c4fa42489c679a808,5ac73dad4fa42489c679a85a,5ac74916cc9da333db2ecbc9,5ac74916cc9da333db2ecbcc,5ac74897cc9da333db2ecbaa,5ac747d34fa42489c679ae3e,5ac7477b4fa42489c679adff,5ac747d9cc9da333db2ecb64,5ac74670cc9da333db2eca85,5ac746654fa42489c679ad17,5ac74630cc9da333db2eca65,5ac745cd4fa42489c679accb,5ac745b64fa42489c679acbf,5ac745804fa42489c679aca3,5ac71c6acc9da333db2eb086,5ac7453dcc9da333db2ec9d0,5ac745364fa42489c679ac76,5ac740d34fa42489c679aa48,5ac743c74fa42489c679abca,5ac7439bcc9da333db2ec908,5ac7421e4fa42489c679aaf7,5ac742644fa42489c679ab1d,5ac72122cc9da333db2eb2cd,5ac734e1cc9da333db2ebf20,5ac740c2cc9da333db2ec7cc,5ac740774fa42489c679aa10,5ac73eeccc9da333db2ec6d4,5ac73d44cc9da333db2ec5b3,5ac73e64cc9da333db2ec67d,5ac73e3f4fa42489c679a8c2,5ac73db3cc9da333db2ec611,5ac73d45cc9da333db2ec5b6,5ac73b574fa42489c679a691,5ac72a07cc9da333db2eb84c,5ac73b41cc9da333db2ec398,5ac73adc4fa42489c679a624,5ac73a01cc9da333db2ec290,5ac739a04fa42489c679a542,5ac73943cc9da333db2ec217,5ac7324f4fa42489c679a0a2,5ac7387b4fa42489c679a448,5ac73208cc9da333db2ebd1f,5ac738704fa42489c679a441,5ac737604fa42489c679a3a7,5ac737b5cc9da333db2ec0f5,5ac737844fa42489c679a3c1,5ac73743cc9da333db2ec0a8,5ac736a94fa42489c679a343,5ac736d64fa42489c679a358,5ac736bfcc9da333db2ec06b,5ac73675cc9da333db2ec02f,5ac735b4cc9da333db2ebfc3,5ac731d1cc9da333db2ebcfd,5ac735984fa42489c679a299,5ac734d04fa42489c679a21d,5ac734f34fa42489c679a23e,5ac73482cc9da333db2ebee6,5ac73449cc9da333db2ebebc,5ac73404cc9da333db2ebe92,5ac733754fa42489c679a152,5ac732ffcc9da333db2ebde8,5ac731ed4fa42489c679a03b,5ac731ff4fa42489c679a044,5ac731bdcc9da333db2ebcf0,5ac73173cc9da333db2ebcce,5ac730dc4fa42489c6799f7e,5ac72ab34fa42489c6799b7e,5ac73043cc9da333db2ebc12,5ac72ec84fa42489c6799e64,5ac72883cc9da333db2eb75b,5ac72da94fa42489c6799dad,5ac72bd74fa42489c6799c50,5ac72bf14fa42489c6799c73,5ac72c8a4fa42489c6799cd7,5ac72c83cc9da333db2eba24,5ac72c81cc9da333db2eba1b,5ac72c5c4fa42489c6799cb4,5ac72be24fa42489c6799c5c,5ac72bfacc9da333db2eb9c3,5ac72bd84fa42489c6799c52,5ac72be34fa42489c6799c61,5ac72b424fa42489c6799bda,5ac72b404fa42489c6799bd7,5ac72b344fa42489c6799bcf,5ac72adccc9da333db2eb8be,5ac72b31cc9da333db2eb8da,5ac72a96cc9da333db2eb88a,5ac729d74fa42489c6799afe,5ac729a1cc9da333db2eb815,5ac72941cc9da333db2eb7e8,5ac727e0cc9da333db2eb70d,5ac727d84fa42489c67999d8,5ac7273b4fa42489c67999ab,5ac72589cc9da333db2eb5ac,5ac725d24fa42489c67998ce,5ac725404fa42489c6799859,5ac71d52cc9da333db2eb0d6,5ac7246d4fa42489c67997f6,5ac71af6cc9da333db2eafe3,5ac723934fa42489c679979a,5ac7236b4fa42489c679976b,5ac7225dcc9da333db2eb38c,5ac7224b4fa42489c6799664,5ac721b4cc9da333db2eb308,5ac721954fa42489c67995ce,5ac72190cc9da333db2eb2f7,5ac720ddcc9da333db2eb2a9,5ac7211f4fa42489c679958f,5ac720034fa42489c67994c2,5ac720694fa42489c67994e7,5ac71fc94fa42489c67994ac,5ac71e3bcc9da333db2eb13c,5ac71efe4fa42489c6799461,5ac71ebdcc9da333db2eb181,5ac71ec1cc9da333db2eb188,5ac71d8b4fa42489c679938d,5ac71d5dcc9da333db2eb0d8,5ac71c674fa42489c679930e,5ac71cbbcc9da333db2eb0a2,5ac71b634fa42489c6799284,5ac71992cc9da333db2eaf2f,5ac719124fa42489c679919b,5ac71857cc9da333db2eaeb3,5ac717c54fa42489c67990f6,5ac717b9cc9da333db2eae6a,5ac717a64fa42489c67990df,5ac716194fa42489c6798fec,5ac716c7cc9da333db2eadf2,5ac716b94fa42489c6799046,5ac7168dcc9da333db2eadce,5ac716384fa42489c6798ff8,5ac7163ecc9da333db2ead9c,5ac71608cc9da333db2ead8b,5ac7163ecc9da333db2ead9f,5ac7141bcc9da333db2eaccf,5ac7125ecc9da333db2eabea,5ac711ddcc9da333db2eaba5,5ac710a84fa42489c6798d1b,5ac6de47cc9da333db2e9074,5ac6e0ff4fa42489c6797555,5ac70ffccc9da333db2eaa8a,5ac70ed2cc9da333db2ea9fb,5ac70e50cc9da333db2ea9d2,5ac70d17cc9da333db2ea969,5ac70cbf4fa42489c6798b7b,5ac70c19cc9da333db2ea8c3,5ac70b7f4fa42489c6798a65,5ac70ba54fa42489c6798a82,5ac6f7934fa42489c6798257,5ac708f8cc9da333db2ea712,5ac6f7d84fa42489c6798261,5ac6fc9d4fa42489c6798418,5ac707e34fa42489c67988dd,5ac702924fa42489c67986b3,5ac702424fa42489c679868f,5ac701e64fa42489c679866b,5ac700eccc9da333db2ea3bd,5ac7007dcc9da333db2ea365,5ac6fe71cc9da333db2ea293,5ac6fe3c4fa42489c67984ef,5ac6fd6ecc9da333db2ea233,5ac6fcb6cc9da333db2ea18c,5ac6fae04fa42489c6798366,5ac6f4334fa42489c6798108,5ac6f19bcc9da333db2e9dd3,5ac6ef55cc9da333db2e9cbf,5ac6eeb84fa42489c6797ec0,5ac6ea9f4fa42489c6797c08,5ac6eddecc9da333db2e9c0a,5ac6ed11cc9da333db2e9b9e,5ac6ecfccc9da333db2e9b8f,5ac6ec604fa42489c6797d40,5ac6ec1dcc9da333db2e9af9,5ac6ea804fa42489c6797bf5,5ac6ebb0cc9da333db2e9ab3,5ac6eb714fa42489c6797c97,5ac6eb604fa42489c6797c7f,5ac6ea1dcc9da333db2e998e,5ac6ea2dcc9da333db2e99a8,5ac6e9fecc9da333db2e9979,5ac6e93d4fa42489c6797b04,5ac6e87e4fa42489c6797a3c,5ac6e861cc9da333db2e979e,5ac6e7e5cc9da333db2e972f,5ac6e4a54fa42489c679778d,5ac6e5f44fa42489c679785f,5ac6e4b6cc9da333db2e94d4,5ac6e695cc9da333db2e9605,5ac6e65e4fa42489c67978ac,5ac6e562cc9da333db2e9548,5ac6e5594fa42489c6797805,5ac6e3ad4fa42489c67976f6,5ac6e2884fa42489c679766b,5ac6e2c74fa42489c6797686,5ac6e20f4fa42489c6797635,5ac6e190cc9da333db2e9312,5ac6e139cc9da333db2e92b5,5ac6e12d4fa42489c679758b,5ac6ddf94fa42489c679730c,5ac6e0df4fa42489c679753d,5ac6e062cc9da333db2e91e4,5ac6dfea4fa42489c6797455,5ac6df9b4fa42489c6797421,5ac6e023cc9da333db2e91bc,5ac6dffe4fa42489c6797464,5ac6de9ecc9da333db2e90ba,5ac6df044fa42489c67973bb,5ac6df1a4fa42489c67973c3,5ac6deee4fa42489c67973ae,5ac6ddf2cc9da333db2e9042,5ac6dedbcc9da333db2e90dd,5ac6ddbbcc9da333db2e900a,5ac6dcb14fa42489c6797219,5ac6dcde4fa42489c6797238,5ac6dcaacc9da333db2e8f63,5ac6dc62cc9da333db2e8f29,5ac6dc2b4fa42489c67971be,5ac6cb1ecc9da333db2e82ea");
        List<String> temp = Lists.newArrayList();
        list.forEach(item -> {
            temp.add(item);
            if (temp.size() == 90) {
                System.out.println(gson.toJson(temp));
                temp.clear();
            }
        });

        System.out.println(gson.toJson(temp));
    }
}