package com.github.lalifeier.mall.demo.interfaces.rest.book.model.request;

import javax.validation.constraints.NotBlank;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@NoArgsConstructor
public class CreateBookRequest {
  @NotBlank(message = "书名不能为空")
  @Length(max = 250, message = "书名最大长度为250")
  private String name;
}
