package com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command;

import com.github.lalifeier.mall.cloud.common.model.ddd.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CreateBookCommand extends BaseBookCommand  implements Command {
}
