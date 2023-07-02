package com.github.lalifeier.mall.cloud.common;

import com.github.lalifeier.mall.cloud.common.manager.ErrorManager;
import com.github.lalifeier.mall.cloud.common.manager.TreeNode;
import com.github.lalifeier.mall.cloud.common.system.HttpErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;
import org.junit.jupiter.api.Test;

import java.util.List;

class ErrorManagerTest {

  @Test
  void getAllErrorCodes() {
    SystemErrorCode.values();
    HttpErrorCode.values();
    SystemErrorCode.values();
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
