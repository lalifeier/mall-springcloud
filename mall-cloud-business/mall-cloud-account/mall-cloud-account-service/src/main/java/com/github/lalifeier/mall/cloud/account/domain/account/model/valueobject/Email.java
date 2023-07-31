package com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.lalifeier.mall.cloud.common.model.marker.ValueObject;
import jakarta.validation.ValidationException;
import java.util.regex.Pattern;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

@Getter
public class Email implements ValueObject<Email> {
    private final String value;

    private static final Pattern VALID_PATTERN =
            Pattern.compile(
                    "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

    public Email(final String value) {
        if (StringUtils.isEmpty(value)) {
            throw new ValidationException("邮箱不能为空");
        }

        Validate.isTrue(VALID_PATTERN.matcher(value).matches(), "邮箱格式不正确");

        this.value = value;
    }

    @Override
    public boolean sameValueAs(Email other) {
        return other != null && this.value.equals(other.value);
    }
}
