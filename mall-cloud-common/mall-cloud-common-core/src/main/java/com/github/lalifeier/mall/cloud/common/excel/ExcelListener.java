package com.github.lalifeier.mall.cloud.common.excel;

import com.alibaba.excel.read.listener.ReadListener;

/** Excel 导入监听 */
public interface ExcelListener<T> extends ReadListener<T> {

  ExcelResult<T> getExcelResult();
}
