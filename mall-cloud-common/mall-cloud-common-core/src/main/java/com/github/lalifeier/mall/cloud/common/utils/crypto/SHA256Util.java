package com.github.lalifeier.mall.cloud.common.utils.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;

public class SHA256Util {
  /**
   * 生成字符串的SHA-256摘要
   *
   * @param data 待加密字符串
   * @return SHA-256摘要
   */
  public static String encode(String data) {
    return DigestUtils.sha256Hex(data);
  }

  /**
   * 计算文件的SHA-256值
   *
   * @param file 待加密文件
   * @return 文件的SHA-256值
   * @throws IOException 如果文件读取错误
   */
  public static String encode(File file) throws IOException {
    FileInputStream fis = new FileInputStream(file);
    String sha256 = DigestUtils.sha256Hex(fis);
    fis.close();
    return sha256;
  }
}
