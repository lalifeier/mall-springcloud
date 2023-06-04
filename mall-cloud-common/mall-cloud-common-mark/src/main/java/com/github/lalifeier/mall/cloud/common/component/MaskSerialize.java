package com.github.lalifeier.mall.cloud.common.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.github.lalifeier.mall.cloud.common.annocation.FieldMask;
import com.github.lalifeier.mall.cloud.common.enums.MaskEnum;
import com.github.lalifeier.mall.cloud.common.utils.MaskUtils;

import java.io.IOException;
import java.util.Objects;

public class MaskSerialize extends JsonSerializer<String> implements ContextualSerializer {

  /**
   * 脱敏类型
   */
  private final MaskEnum type;

  public MaskSerialize() {
    this.type = null;
  }

  public MaskSerialize(final MaskEnum type) {
    this.type = type;
  }

  @Override
  public void serialize(final String s, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
    switch (this.type) {
      case CHINESE_NAME: {
        jsonGenerator.writeString(MaskUtils.chineseName(s));
        break;
      }
      case ID_CARD: {
        jsonGenerator.writeString(MaskUtils.idCardNum(s));
        break;
      }
      case FIXED_PHONE: {
        jsonGenerator.writeString(MaskUtils.fixedPhone(s));
        break;
      }
      case MOBILE_PHONE: {
        jsonGenerator.writeString(MaskUtils.mobilePhone(s));
        break;
      }
      case ADDRESS: {
        jsonGenerator.writeString(MaskUtils.address(s, 4));
        break;
      }
      case EMAIL: {
        jsonGenerator.writeString(MaskUtils.email(s));
        break;
      }
      case BANK_CARD: {
        jsonGenerator.writeString(MaskUtils.bankCard(s));
        break;
      }
      case API_SECRET: {
        jsonGenerator.writeString(MaskUtils.apiSecret(s));
        break;
      }
      default:
        jsonGenerator.writeString(s);
        break;
    }
  }

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
    // 非String类直接跳过
    if (!Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
      return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }

    // 为空直接跳过
    if (beanProperty == null) {
      return serializerProvider.findNullValueSerializer(beanProperty);
    }

    FieldMask fieldMask = beanProperty.getAnnotation(FieldMask.class);
    if (fieldMask == null) {
      fieldMask = beanProperty.getContextAnnotation(FieldMask.class);
    }

    if (fieldMask != null) {
      // 如果能得到注解，就将注解的 value 传入 MaskSerialize
      return new MaskSerialize(fieldMask.value());
    }

    return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
  }
}

