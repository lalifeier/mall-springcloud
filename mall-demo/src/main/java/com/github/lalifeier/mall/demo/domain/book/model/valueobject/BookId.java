package com.github.lalifeier.mall.demo.domain.book.model.valueobject;

import com.github.lalifeier.mall.common.model.Identifier;
import lombok.Getter;
import net.sf.jsqlparser.util.validation.ValidationException;



@Getter
public class BookId implements Identifier<Long> {
  private final Long value;

  public BookId(final Long value) {
    if (value == null) {
      throw new ValidationException("BookId不能为空");
    }
    this.value = value;
  }
}
