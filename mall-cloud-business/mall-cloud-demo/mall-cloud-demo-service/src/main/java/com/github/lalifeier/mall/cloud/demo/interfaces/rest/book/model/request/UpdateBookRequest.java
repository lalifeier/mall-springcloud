package com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class UpdateBookRequest {
  @NotBlank(message = "书名不能为空")
  @Length(max = 250, message = "书名最大长度为250")
  private String name;
}
