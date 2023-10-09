package com.github.lalifeier.mall.cloud.demo.interfaces.rest;

import com.github.lalifeier.mall.cloud.common.enums.StatusEnum;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("")
public class EnumController {
    @GetMapping("/getEnum")
    StatusEnum getEnum(@RequestParam("status") @NotNull(message = "status不能为空") StatusEnum status) {
        return status;
    }
}
