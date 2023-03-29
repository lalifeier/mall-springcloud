package com.github.lalifeier.mall.common.error;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.lalifeier.mall.common.error.manager.ErrorManager;
import com.github.lalifeier.mall.common.error.manager.TreeNode;
import com.github.lalifeier.mall.common.error.system.HttpCodes;
import com.github.lalifeier.mall.common.error.system.SystemErrorCodes;
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
