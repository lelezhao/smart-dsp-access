package cn.zhiyingyun.zone.controller;


import cn.zhiyingyun.zone.entity.UpPlatResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joy.Zhao on 2017/7/3.
 */
@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestController extends BaseController {

  private static final Logger log = LoggerFactory.getLogger(TestController.class);

  StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

  private RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(stringHttpMessageConverter).build();

  @GetMapping(value = "/")
  public String test() {
    String url = "http://localhost:8080/ad/request";

    String body = "{\"id\":\"5ee58e9f-b8af-4676-b12d-99e7d3a6561d-1520284914188\",\"imp\":[{\"id\":\"e74e5960-5fcc-4b71-bd3d-f787ba97a33a\",\"tagid\":\"40E6F47CD8CC59E091A7336984A6AFB8\",\"native\":{\"title\":{\"len\":30},\"img\":{\"w\":720,\"h\":240}},\"instl\":7,\"bidfloor\":6.0,\"is_support_deeplink\":1,\"secure\":0,\"is_downloadable\":1}],\"app\":{\"id\":\"5541a4bb\",\"name\":\"讯飞输入法\",\"bundle\":\"com.iflytek.inputmethod\",\"version\":\"8.0.6511\",\"cat\":[\"IAB24\"]},\"device\":{\"w\":1080,\"h\":1920,\"ua\":\"Mozilla/5.0 (Linux; Android 7.1.1; PRO 6s Build/NMF26O; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704.110 Mobile Safari/537.36\",\"ip\":\"42.239.4.233\",\"did\":\"862452031177975\",\"didmd5\":\"469a9b4472a55c7e6b4673984b951f0c\",\"didsha1\":\"69b2379690bfa8c9a1ee3b6e79bc81bcfe28e0e2\",\"dpid\":\"ac092aff044f0a3c\",\"dpidmd5\":\"e64ad185bf5c737cc3d2a2a0ea4ce5a1\",\"dpidsha1\":\"2934a15f16ca517976432a4fed4f6a68d5c466fb\",\"make\":\"魅族\",\"model\":\"PRO 6s\",\"os\":\"android\",\"osv\":\"7.1.1\",\"carrier\":\"46000\",\"language\":\"zh-CN\",\"connectiontype\":2,\"devicetype\":0}}";

    JSONObject.toJSONString(JSONObject.parseObject(body));

    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, JSONObject.toJSONString(JSONObject.parseObject(body)), String.class);

    return "hello,world";
  }

  @GetMapping(value = "/2")
  public String test2() {
    String url = "http://192.168.59.5/jingzan7?";

    String body = "{\"id\":\"0ab153c2-42c6-4d66-b437-c92e92c06b73-1520330862288\",\"imp\":[{\"id\":\"7674da6b-a149-43ab-bacc-3847b5e3b32d\",\"bindustry\":[799,710,70911],\"tagid\":\"D8AB6A40E4E813FAD818F14230BE452B\",\"video\":{\"protocol\":101,\"w\":640,\"h\":480,\"minduration\":15000,\"maxduration\":15000,\"linearity\":1,\"sizes\":[{\"w\":1280,\"h\":720}]},\"instl\":19,\"bidfloor\":0.01,\"pmp\":{},\"is_support_deeplink\":0,\"secure\":0,\"is_downloadable\":1}],\"app\":{\"id\":\"5a94c51e\",\"name\":\"letvVideo\",\"version\":\"5810\"},\"device\":{\"w\":720,\"h\":1280,\"ua\":\"android/4.1.1 (amsung;GT-I9300) letvVideo/5810/aps_cm_3.0.5.6\",\"ip\":\"111.37.17.173\",\"geo\":{\"lat\":0.0,\"lon\":0.0},\"did\":\"354316054366184\",\"didmd5\":\"e4e3f1a80085b943a11211b95e8f86e6\",\"didsha1\":\"d88fe8ae807f0878f84f68448e02354d35c67878\",\"dpid\":\"d5bae2f169a62ada\",\"dpidmd5\":\"4d5331bf62f56ee4be2f4fdcf2eaf128\",\"dpidsha1\":\"13fe2a9918ee74046581dd075adfeda06533716f\",\"mac\":\"68:df:dd:69:8a:bc\",\"macmd5\":\"3ccd6a94b2cbc53026d6cc9abc64dc90\",\"macsha1\":\"78db3f76bf8cf8395487c0f8bb0dfcc83f506371\",\"make\":\"三星\",\"model\":\"GT-I9300\",\"os\":\"android\",\"osv\":\"4.1.1\",\"carrier\":\"46003\",\"language\":\"\",\"connectiontype\":2,\"devicetype\":0},\"user\":{\"ext\":{}},\"debug\":{\"action_type\":1}}";

    MediaType type = MediaType.APPLICATION_JSON_UTF8;

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(type);
    httpHeaders.add("content-type", "application/json");
    httpHeaders.add("x-openrtb-version", "2.3.2");
    httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());


    HttpEntity<String> requstEntity = new HttpEntity<String>(body, httpHeaders);

    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requstEntity, String.class);

    return responseEntity.getBody();
  }

  @GetMapping(value = "/banner")
  public String banner() {
    String url = "http://rtb.voiceads.cn/xf";

    String body = "{\"id\":\"00241805-60dc-41ba-978b-7956bd6854c5-1520477247424\",\"imp\":[{\"id\":\"4ffa2e07-c719-42d2-ba0c-520a9e07705b\",\"tagid\":\"58349F1F392F79EE10715232FB249531\",\"banner\":{\"type\":1,\"w\":640,\"h\":100,\"matypes\":[1],\"formats\":[0,1,2]},\"instl\":1,\"bidfloor\":0.01,\"itavoiceable\":true,\"pmp\":{},\"is_support_deeplink\":0,\"secure\":0,\"is_downloadable\":1}],\"app\":{\"id\":\"5a38c514\",\"name\":\"讯飞输入法\",\"bundle\":\"cn.com.caijing.android\",\"version\":\"120\"},\"device\":{\"w\":1440,\"h\":2392,\"ua\":\"Mozilla/5.0 (Linux; Android 5.0.1; LG-D857 Build/LRX22G) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36\",\"ip\":\"122.200.86.177\",\"geo\":{\"lat\":32.0,\"lon\":112.0},\"did\":\"354834060683566\",\"didmd5\":\"cc8b0144001d03ccce03fd31a188329d\",\"didsha1\":\"6c36e589e6bb3b4f878963e722f9aa7c4f302975\",\"dpid\":\"4190891b4bdc72fb\",\"dpidmd5\":\"38d0b0a2858e76b1dd2fbbc01db77047\",\"dpidsha1\":\"98917c22c9b0b329747bb98d6b3aebbb13c8281e\",\"mac\":\"70:8a:09:32:a1:31\",\"macmd5\":\"d8ff5d0439ec152d43f657c5e85f35bf\",\"macsha1\":\"6371db07dca1eda0104a1782647e9d7bebd0fb26\",\"ifa\":\"123\",\"make\":\"LGE\",\"model\":\"LGE p1\",\"os\":\"android\",\"osv\":\"android5.0.1\",\"carrier\":\"46001\",\"language\":\"zh-CN\",\"connectiontype\":2,\"devicetype\":0},\"user\":{\"ext\":{}}}";

    MediaType type = MediaType.APPLICATION_JSON_UTF8;

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(type);
    httpHeaders.add("content-type", "application/json");
    httpHeaders.add("x-openrtb-version", "2.3.2");
    httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());


    HttpEntity<String> requstEntity = new HttpEntity<String>(body, httpHeaders);

    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requstEntity, String.class);

    checkResponse(responseEntity.getBody());

    return responseEntity.getBody();
  }


  @GetMapping(value = "/info")
  public String info() {
    String url = "http://rtb.voiceads.cn/xf";

    String body = "{\"id\":\"bf3cd0e0-256f-4398-a222-1a0ecc0fb7b3-1520477604582\",\"imp\":[{\"id\":\"95cb5cc4-ab31-4ddd-a8c3-afba108ba1b8\",\"tagid\":\"4D5F4C1B500D082EB94829ABA3BA5121\",\"native\":{\"title\":{\"len\":30},\"desc\":{\"len\":30},\"img\":{\"w\":600,\"h\":500},\"formats\":[0,1,2]},\"instl\":8,\"bidfloor\":0.01,\"itavoiceable\":true,\"pmp\":{},\"is_support_deeplink\":0,\"secure\":0,\"is_downloadable\":1}],\"app\":{\"id\":\"5a38c514\",\"name\":\"讯飞输入法\",\"bundle\":\"cn.com.caijing.android\",\"version\":\"120\"},\"device\":{\"w\":1440,\"h\":2392,\"ua\":\"Mozilla/5.0 (Linux; Android 5.0.1; LG-D857 Build/LRX22G) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36\",\"ip\":\"122.200.86.177\",\"geo\":{\"lat\":32.0,\"lon\":112.0},\"did\":\"354834060683566\",\"didmd5\":\"cc8b0144001d03ccce03fd31a188329d\",\"didsha1\":\"6c36e589e6bb3b4f878963e722f9aa7c4f302975\",\"dpid\":\"4190891b4bdc72fb\",\"dpidmd5\":\"38d0b0a2858e76b1dd2fbbc01db77047\",\"dpidsha1\":\"98917c22c9b0b329747bb98d6b3aebbb13c8281e\",\"mac\":\"70:8a:09:32:a1:31\",\"macmd5\":\"d8ff5d0439ec152d43f657c5e85f35bf\",\"macsha1\":\"6371db07dca1eda0104a1782647e9d7bebd0fb26\",\"ifa\":\"123\",\"make\":\"LGE\",\"model\":\"LGE p1\",\"os\":\"android\",\"osv\":\"android5.0.1\",\"carrier\":\"46001\",\"language\":\"zh-CN\",\"connectiontype\":2,\"devicetype\":0},\"user\":{\"ext\":{}}}";

    MediaType type = MediaType.APPLICATION_JSON_UTF8;

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(type);
    httpHeaders.add("content-type", "application/json");
    httpHeaders.add("x-openrtb-version", "2.3.2");
    httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());


    HttpEntity<String> requstEntity = new HttpEntity<String>(body, httpHeaders);

    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requstEntity, String.class);

    return responseEntity.getBody();
  }

  private void checkResponse(String response) {
    Map<String, String> errorMap = new HashMap<>();

    if (StringUtils.isBlank(response)) {
      log.debug("001", "响应内容为空");
    }

    UpPlatResponse upPlatResponse = UpPlatResponse.fromString(response);

    if (upPlatResponse == null) {
      log.debug("response转UplatResponse返回null,sid={}");
    }

    if (upPlatResponse.seatbid == null || upPlatResponse.seatbid.size() == 0) {
      log.debug("seatbid为空,sid={}");
    }
    if (upPlatResponse.seatbid.get(0).bid == null
            || upPlatResponse.seatbid.get(0).bid.size() == 0) {
      log.debug("bid为空,sid={}");
    }
  }
}
