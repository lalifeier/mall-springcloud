package com.github.lalifeier.mall.cloud.common.manager;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ErrorManager {
    private static final BiMap<Integer, ErrorCode> GLOBAL_ERROR_CODE_MAP = HashBiMap.create();
    private static final Map<ErrorCode, ProjectModule> ERROR_PROJECT_MODULE_MAP = new ConcurrentHashMap<>();

    private static final Comparator<ProjectModule> PROJECT_MODULE_COMPARATOR =
            Comparator.comparingInt(ProjectModule::getProjectCode).thenComparingInt(ProjectModule::getModuleCode);
    private static final Comparator<ErrorCode> ERROR_CODE_COMPARATOR = Comparator.comparingInt(ErrorCode::getNodeNum);

    public static void register(ProjectModule projectModule, ErrorCode ErrorCode) {
        Preconditions.checkNotNull(projectModule);
        Preconditions.checkArgument(projectModule.getProjectCode() >= 0);
        Preconditions.checkArgument(projectModule.getModuleCode() >= 0);
        Preconditions.checkArgument(ErrorCode.getNodeNum() >= 0);
        int code = genCode(projectModule, ErrorCode);
        Preconditions.checkArgument(!GLOBAL_ERROR_CODE_MAP.containsKey(code), "错误码重复:" + code);
        GLOBAL_ERROR_CODE_MAP.put(code, ErrorCode);
        ERROR_PROJECT_MODULE_MAP.put(ErrorCode, projectModule);
    }

    public static List<TreeNode> getAllErrorCodes() {
        return ERROR_PROJECT_MODULE_MAP.entrySet().stream()
                .sorted((it1, it2) -> ERROR_CODE_COMPARATOR.compare(it1.getKey(), it2.getKey()))
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet()
                .stream()
                .sorted((it1, it2) -> PROJECT_MODULE_COMPARATOR.compare(it1.getKey(), it2.getKey()))
                .collect(Collectors.groupingBy(
                        e -> new TreeNode(
                                e.getKey().getProjectCode(), e.getKey().getProjectName()),
                        Collectors.groupingBy(
                                it -> new TreeNode(
                                        it.getKey().getModuleCode(), it.getKey().getModuleName()),
                                Collectors.mapping(Map.Entry::getValue, Collectors.toList()))))
                .entrySet()
                .stream()
                .map(e -> {
                    TreeNode top = e.getKey();
                    List<TreeNode> middleNode = e.getValue().entrySet().stream()
                            .map(e1 -> {
                                TreeNode key = e1.getKey();
                                List<TreeNode> leftNode = e1.getValue().stream()
                                        .flatMap(Collection::stream)
                                        .map(error -> new TreeNode(error.getCode(), error.getMessage()))
                                        .collect(Collectors.toList());
                                key.setNodes(leftNode);
                                return key;
                            })
                            .collect(Collectors.toList());
                    top.setNodes(middleNode);
                    return top;
                })
                .collect(Collectors.toList());
    }

    private static int genCode(ProjectModule projectModule, ErrorCode ErrorCode) {
        return projectModule.getProjectCode() * 10000 + projectModule.getModuleCode() * 100 + ErrorCode.getNodeNum();
    }

    public static int genCode(ErrorCode ErrorCode) {
        return GLOBAL_ERROR_CODE_MAP.inverse().get(ErrorCode);
    }

    public static ProjectModule projectModule(ErrorCode ErrorCode) {
        return ERROR_PROJECT_MODULE_MAP.get(ErrorCode);
    }
}
