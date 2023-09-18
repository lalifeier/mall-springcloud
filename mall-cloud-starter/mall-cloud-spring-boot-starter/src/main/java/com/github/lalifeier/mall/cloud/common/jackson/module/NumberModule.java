package com.github.lalifeier.mall.cloud.common.jackson.module;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *  大整数序列化为 String 字符串，避免浏览器丢失精度
 */
public class NumberModule extends SimpleModule {
    @Serial
    private static final long serialVersionUID = 1L;

    public NumberModule() {
        super(PackageVersion.VERSION);

        // Long 和 BigInteger 采用定制的逻辑序列化，避免超过js的精度
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.addSerializer(BigInteger.class, ToStringSerializer.instance);
        // BigDecimal 采用 toString 避免精度丢失，前端采用 decimal.js 来计算
        this.addSerializer(BigDecimal.class, ToStringSerializer.instance);
    }
}
