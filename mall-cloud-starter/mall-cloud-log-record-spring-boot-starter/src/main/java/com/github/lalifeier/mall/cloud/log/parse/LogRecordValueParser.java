package com.github.lalifeier.mall.cloud.log.parse;

import com.github.lalifeier.mall.cloud.log.annotation.OperationLog;
import com.github.lalifeier.mall.cloud.log.model.ExecuteResult;
import com.github.lalifeier.mall.cloud.log.model.MethodInfo;
import com.github.lalifeier.mall.cloud.log.service.FunctionService;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/** 解析 {@link OperationLog} 中的 SpEl 表达式 */
@RequiredArgsConstructor
public class LogRecordValueParser implements BeanFactoryAware {

  protected BeanFactory beanFactory;
  private final FunctionService functionService;
  private final LogRecordExpressionEvaluator expressionEvaluator =
      new LogRecordExpressionEvaluator();

  private static final Pattern PATTERN = Pattern.compile("\\{\\s*(\\w*)\\s*\\{(.*?)}}");

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }

  public Map<String, String> processTemplate(Collection<String> templates,
      ExecuteResult executeResult, Map<String, String> beforeFunctionNameAndReturnMap) {
    return null;
  }

  public Map<String, String> processBeforeExecuteFunctionTemplate(Collection<String> templates,
      MethodInfo methodInfo) {
    return null;
  }
}
