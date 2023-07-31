package com.github.lalifeier.mall.cloud.demo.book.dto.command;

import com.github.lalifeier.mall.cloud.common.model.marker.Command;
import lombok.Data;

@Data
public class CreateBookCommand implements Command {

    private String name;
}
