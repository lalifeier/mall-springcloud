package com.github.lalifeier.mall.cloud.common.jackson.module;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.lalifeier.mall.cloud.common.jackson.EnumDeserializer;
import com.github.lalifeier.mall.cloud.common.jackson.EnumSerializer;
import com.github.lalifeier.mall.cloud.common.jackson.SimpleDeserializersWrapper;
import java.io.Serial;

public class EnumModule extends SimpleModule {
    @Serial
    private static final long serialVersionUID = 1L;

    public EnumModule() {
        super(PackageVersion.VERSION);

        this.setDeserializers(new SimpleDeserializersWrapper());
        this.addSerializer(Enum.class, EnumSerializer.INSTANCE);
        this.addDeserializer(Enum.class, EnumDeserializer.INSTANCE);
    }
}
