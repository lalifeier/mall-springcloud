package com.github.lalifeier.mall.cloud.common.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Consumer;

/**
 * Excel工具类
 */
public class ExcelUtil extends EasyExcel {
  private ExcelUtil() {
  }

  public static <T> ExcelReaderBuilder read(String pathName, Class<T> head, Integer pageSize, Consumer<List<T>> consumer) {
    return read(pathName, head, new EasyExcelConsumerListener<>(pageSize, consumer));
  }

  public static <T> ExcelReaderBuilder read(File file, Class<T> head, Integer pageSize, Consumer<List<T>> consumer) {
    return read(file, head, new EasyExcelConsumerListener<>(pageSize, consumer));
  }

  public static <T> ExcelReaderBuilder read(InputStream inputStream, Class<T> head, Integer pageSize, Consumer<List<T>> consumer) {
    return read(inputStream, head, new EasyExcelConsumerListener<>(pageSize, consumer));
  }

  public static <T> void readExcel(InputStream inputStream, Class<T> clazz) {
  }

  public static void writeExcel(OutputStream outputStream, List<Object> data, Class<?> clazz) {
    ExcelWriter excelWriter = EasyExcel.write(outputStream, clazz).build();
    WriteSheet writeSheet = EasyExcel.writerSheet().build();
    excelWriter.write(data, writeSheet);
    excelWriter.finish();
  }

//  public static void writeExcel(String filePath, List<Object> data, Class<?> clazz) {
//    ExcelWriter excelWriter = EasyExcel.write(filePath, clazz).build();
//    WriteSheet writeSheet = EasyExcel.writerSheet().build();
//    excelWriter.write(data, writeSheet);
//    excelWriter.finish();
//  }

//  private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
//  }
}
