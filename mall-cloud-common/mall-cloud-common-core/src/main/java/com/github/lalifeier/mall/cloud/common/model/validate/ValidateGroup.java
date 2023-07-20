package com.github.lalifeier.mall.cloud.common.model.validate;

import javax.validation.groups.Default;

/**
 * 分组校验
 */
public interface ValidateGroup extends Default {

  interface Crud extends ValidateGroup {
    interface Create extends Crud {

    }

    interface Update extends Crud {

    }

    interface Query extends Crud {

    }

    interface Delete extends Crud {

    }
  }
}
