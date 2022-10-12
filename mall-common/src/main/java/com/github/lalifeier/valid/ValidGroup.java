package com.github.lalifeier.valid;

import javax.validation.groups.Default;

public interface ValidGroup extends Default {
    interface Crud extends ValidGroup{
        /**
         * 添加入参分组
         */
        interface Create extends Crud{

        }

        /**
         * 修改入参分组
         */
        interface Update extends Crud{

        }

        /**
         * 查询入参分组
         */
        interface Query extends Crud{

        }

        /**
         * 删除入参分组
         */
        interface Delete extends Crud{

        }
    }
}

