package com.github.lalifeier.mall.cloud.mybatisplus.injector;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import java.util.List;

public class MySqlInjector extends DefaultSqlInjector {
  @Override
  public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
    List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
    methodList.add(new InsertBatchSomeColumn(i -> i.getFieldFill() != FieldFill.UPDATE));
    // methodList.add(new UpdateBatchMethod(i -> i.getFieldFill() != FieldFill.INSERT));
    methodList.add(new AlwaysUpdateSomeColumnById());
    return methodList;
  }
}
