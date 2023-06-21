package com.github.lalifeier.mall.cloud.common.utils.crypto;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MD5Util {
  /**
   * 生成字符串的MD5摘要
   *
   * @param data 待加密字符串
   * @return MD5摘要
   */
  public static String encode(String data) {
    return DigestUtils.md5Hex(data);
  }

  /**
   * 计算文件的MD5值
   *
   * @param file 待加密文件
   * @return 文件的MD5值
   * @throws IOException 如果文件读取错误
   */
  public static String encode(File file) throws IOException {
    FileInputStream fis = new FileInputStream(file);
    String md5 = DigestUtils.md5Hex(fis);
    fis.close();
    return md5;
  }
}

