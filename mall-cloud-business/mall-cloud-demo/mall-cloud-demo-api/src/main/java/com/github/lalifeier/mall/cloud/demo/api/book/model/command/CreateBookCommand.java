package com.github.lalifeier.mall.cloud.demo.api.book.model.command;

import com.github.lalifeier.mall.cloud.common.model.marker.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBookCommand implements Command {
    @NotBlank(message = "书籍名称不能为空")
    @Schema(description = "书籍名称", required = true)
    private String name;
}
