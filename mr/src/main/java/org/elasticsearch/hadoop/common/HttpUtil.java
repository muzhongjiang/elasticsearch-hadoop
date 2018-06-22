package org.elasticsearch.hadoop.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpUtil {

  private static Log LOG = LogFactory.getLog(HttpUtil.class);


  /**
   * 向指定 URL 发送POST方法的请求
   *
   * @param url 送请求的 URL
   * @param jsonParam  求参数
   */
  public static String sendPost(String url, String jsonParam) {
    if(jsonParam==null){
      LOG.error("jsonParam为空");
      return null;
    }
    PrintWriter out = null;
    BufferedReader in = null;
    StringBuffer result = new StringBuffer();
    try {
      URL realUrl = new URL(url);
      // 打开和URL之间的连接
      URLConnection conn = realUrl.openConnection();
      // 设置通用的请求属性
      conn.setRequestProperty("accept", "*/*");
      conn.setRequestProperty("connection", "Keep-Alive");
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
      // 发送POST请求必须设置如下两行
      conn.setDoOutput(true);
      conn.setDoInput(true);
      // 获取URLConnection对象对应的输出流
      out = new PrintWriter(conn.getOutputStream());
      out.print(jsonParam); // 发送请求参数
      out.flush();  // flush输出流的缓冲
      // 定义BufferedReader输入流来读取URL的响应
      in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result.append(line);
      }
    } catch (Exception e) {
      LOG.error(e);
    }
    //使用finally块来关闭输出流、输入流
    finally {
      try {
        if (out != null) {
          out.close();
        }
        if (in != null) {
          in.close();
        }
      } catch (IOException e) {
        LOG.error(e);
      }
    }
    return result.toString();
  }
}