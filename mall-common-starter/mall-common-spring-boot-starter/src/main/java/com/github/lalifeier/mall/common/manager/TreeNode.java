package com.github.lalifeier.mall.common.manager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

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