package com.github.lalifeier.mall.demo.interfaces.api.book.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdateBookRequest {
  @NotBlank(message = "书名不能为空")
  @Length(max = 250, message = "书名最大长度为250")
  private String name;
}