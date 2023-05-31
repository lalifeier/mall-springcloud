package com.github.lalifeier.mall.cloud.common;

import java.util.List;

import com.github.lalifeier.mall.cloud.common.manager.ErrorManager;
import com.github.lalifeier.mall.cloud.common.manager.TreeNode;
import com.github.lalifeier.mall.cloud.common.system.HttpCodes;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCodes;
import org.junit.jupiter.api.Test;

class ErrorManagerTest {

   @Test
   void getAllErrorCodes() {
       SystemErrorCodes.values();
       HttpCodes.values();
       SystemErrorCodes.values();
       List<TreeNode> allErrorCodes = ErrorManager.getAllErrorCodes();
       System.out.println(allErrorCodes);
      //  assertEquals(2, allErrorCodes.size());

       for (TreeNode treeNode : allErrorCodes) {
           System.out.println("1." + treeNode);
           for (TreeNode node : treeNode.getNodes()) {
               System.out.println("2." + treeNode);
               for (TreeNode left : node.getNodes()) {
                   System.out.println("3." + left);
               }
           }
       }
   }
}
