/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.hadoop.mr;

import org.elasticsearch.hadoop.common.AlertUtil;

public class AlertTest {

  @org.junit.Test
  public void testDingAlert() throws Exception {
    String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=b52220f0890948c38ec9b056f71df24022540b616c483eea5f3639af1ef689f4";
    String esResourceWrite = "van-gogh-driver-2017.06.06/user";
    String text = "[梵高推数提示](Hive2ES.... Retry ....index=【" + esResourceWrite + "】)";
    AlertUtil.sendMessesgeByDing(dingUrl, text);
  }

}
