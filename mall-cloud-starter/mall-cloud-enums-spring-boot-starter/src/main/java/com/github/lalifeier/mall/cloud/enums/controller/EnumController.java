package com.github.lalifeier.mall.cloud.enums.controller;

import com.github.lalifeier.mall.cloud.enums.context.EnumContext;
import com.github.lalifeier.mall.cloud.enums.context.EnumContextFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enum")
public class EnumController {
    @RequestMapping("/{name}")
    public void getEnum(@PathVariable String name) {
        EnumContext enumContext = EnumContextFactory.getEnumContext();

        try {
            Class<?> enumClass = enumContext.getEnum(name);
        } catch (Exception e) {

        }
    }
}
