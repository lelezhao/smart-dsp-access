package cn.zhiyingyun.zone.service.impl;

import cn.zhiyingyun.zone.service.IBuildRequestService;
import org.springframework.stereotype.Service;

@Service
public class BuildRequestServiceImpl implements IBuildRequestService {
  public void buildBannerImp(boolean isSupportDeepLink, boolean isDowmloadable, int secure) {
    String json = "{\n" +
            "    \"id\":\"20b453e6-7cb9-444c-9ae8-0f26f72f79f6\",\n" +
            "    \"tagid\":\"XXXXXXXXXXXXXXXXXXXXXXXXX\",\n" +
            "    \"banner\":{\n" +
            "        \"type\":1,\n" +
            "        \"w\":640,\n" +
            "        \"h\":100\n" +
            "    },\n" +
            "    \"instl\":1,\n" +
            "    \"bidfloor\":0.3,\n" +
            "    \"is_support_deeplink\":0,\n" +
            "    \"secure\":0,\n" +
            "    \"is_downloadable\":1\n" +
            "}";
  }

  public void buildNativeImp(boolean isSupportDeepLink, boolean isDowmloadable, int secure) {
    String json = "{\n" +
            "\"id\": \"4fd216e0-baf9-483e-9c88-43e8421b9292\",\n" +
            "\"tagid\": \"XXXXXXXXXXXXXXXXXXX\",\n" +
            "\"native\": {\n" +
            "\"title\": {\n" +
            "\"len\": 30\n" +
            "},\n" +
            "\"img\": {\n" +
            "\"w\": 640,\n" +
            "\"h\": 960\n" +
            "}\n" +
            "},\n" +
            "\"instl\": 7,\n" +
            "\"bidfloor\": 13.0,\n" +
            "\"is_support_deeplink\": 1,\n" +
            "\"secure\": 0,\n" +
            "\"is_downloadable\": 1\n" +
            "}";
  }

  public void buildVideoImp(boolean isSupportDeepLink, boolean isDowmloadable, int secure) {
    String josn = "{\n" +
            "    \"id\":\"9f06a56f-2248-4834-978e-770bd5cc8ecd\",\n" +
            "    \"instl\":2,\n" +
            "    \"video\":{\n" +
            "        \"protocol\":101,\n" +
            "        \"w\":600,\n" +
            "        \"h\":500,\n" +
            "        \"minduration\":5000,\n" +
            "        \"maxduration\":15000,\n" +
            "        \"linearation\":1\n" +
            "    },\n" +
            "    \"bidfloor\":0.1\n" +
            "}";
  }

  public void buildDevice(String carrier, Integer connnectionType, String os) {
    String json = "{\n" +
            "    \"w\":1080,\n" +
            "    \"h\":1776,\n" +
            "    \"ua\":\"Mozilla/5.0 (Linux; Android 6.0.1; ATH-AL00 Build/HONORATH-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36\",\n" +
            "    \"ip\":\"14.30.158.152\",\n" +
            "    \"geo\":{\n" +
            "        \"lat\":23.043595,\n" +
            "        \"lon\":112.869539\n" +
            "    },\n" +
            "    \"did\":\"863546034395903\",\n" +
            "    \"didmd5\":\"5656cc117aa7fdff17383d7ae66f1514\",\n" +
            "    \"didsha1\":\"b889b1fff1c90838ef677bf7f1deeddac638a156\",\n" +
            "    \"dpid\":\"7743974f06212051\",\n" +
            "    \"dpidmd5\":\"0acd1ad238ce94bc5764357462456300\",\n" +
            "    \"dpidsha1\":\"0e7ba7f58fdde994a51abfd1c0f14d9f90b51d2d\",\n" +
            "    \"mac\":\"9c:b2:b2:b4:cc:c6\",\n" +
            "    \"macmd5\":\"6018660532b77322ef8c327513ac0d4b\",\n" +
            "    \"macsha1\":\"2eb4cb2f061105179b9707c8450a605fca65b45a\",\n" +
            "    \"ifa\":\"0\",\n" +
            "    \"make\":\"HUAWEI\",\n" +
            "    \"model\":\"ATH-AL00\",\n" +
            "    \"os\":\"android\",\n" +
            "    \"osv\":\"6.0.1\",\n" +
            "    \"carrier\":\"46003\",\n" +
            "    \"language\":\"zh-CN\",\n" +
            "    \"connectiontype\":4,\n" +
            "    \"devicetype\":0\n" +
            "}";
  }

  public void buildApp() {
    String json = "{\n" +
            "    \"id\":\"58ed8ca5\",\n" +
            "    \"name\":\"测试 APP\",\n" +
            "    \"bundle\":\"com.test\",\n" +
            "    \"version\":\"1.0.0\",\n" +
            "    \"cat\":[\n" +
            "        \"IAB5\"\n" +
            "    ]\n" +
            "}";
  }

  public void buildPmp() {
    String json = "{\n" +
            "\"id\": \"123456789\",\n" +
            "\"bidfloor\": 0.1\n" +
            "}";
  }

  public void buildUser() {

  }
}
