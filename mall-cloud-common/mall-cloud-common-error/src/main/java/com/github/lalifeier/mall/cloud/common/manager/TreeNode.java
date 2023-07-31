package com.github.lalifeier.mall.cloud.common.manager;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "code")
public class TreeNode {
    int code;
    String name;
    List<TreeNode> nodes;

    public TreeNode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }
}
