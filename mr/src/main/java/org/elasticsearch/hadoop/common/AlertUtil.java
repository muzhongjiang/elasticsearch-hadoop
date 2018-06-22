package org.elasticsearch.hadoop.common;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 报警
 * @author muzhongjiang
 * @version create-date:2018-06-19
 **/
public class AlertUtil {

  private static Log LOG = LogFactory.getLog(AlertUtil.class);

  /**
   * 发送消息到 Ding
   * @author muzhongjiang
   * @version create-date:2018-06-19
   **/
  public static void sendMessesgeByDing(String url, String text) {
    LOG.info("发送告警信息到 Ding url=【" + url + "】text=【" + text + "】");

    try {
      Map<String, Object> textJson = new HashMap<>();
      textJson.put("content", text);
      //
      Map<String, Object> textMsg = new HashMap<>();
      textMsg.put("msgtype", "text");
      textMsg.put("text", textJson);
      String json = new ObjectMapper().writeValueAsString(textMsg);
      String resultValue = HttpUtil.sendPost(url, json);
      LOG.info("Ding result = " + resultValue);

    } catch (Exception e) {
      LOG.error("发送告警信息到 Ding 出错... text=【" + text + "】", e);
    }
  }
}
