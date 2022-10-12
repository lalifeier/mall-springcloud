package com.github.lalifeier.manager;


import com.github.lalifeier.api.IError;
import com.github.lalifeier.api.ProjectModule;
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
    private static final BiMap<Integer, IError> GLOBAL_ERROR_CODE_MAP = HashBiMap.create();
    private static final Map<IError, ProjectModule> ERROR_PROJECT_MODULE_MAP = new ConcurrentHashMap<>();

    private static final Comparator<ProjectModule> PROJECT_MODULE_COMPARATOR = Comparator.comparingInt(ProjectModule::getProjectCode)
            .thenComparingInt(ProjectModule::getModuleCode);
    private static final Comparator<IError> ERROR_CODE_COMPARATOR = Comparator.comparingInt(IError::getNodeNum);

    public static void register(ProjectModule projectModule, IError IError) {
        Preconditions.checkNotNull(projectModule);
        Preconditions.checkArgument(projectModule.getProjectCode() >= 0);
        Preconditions.checkArgument(projectModule.getModuleCode() >= 0);
        Preconditions.checkArgument(IError.getNodeNum() >= 0);
        int code = genCode(projectModule, IError);
        Preconditions.checkArgument(!GLOBAL_ERROR_CODE_MAP.containsKey(code), "错误码重复:" + code);
        GLOBAL_ERROR_CODE_MAP.put(code, IError);
        ERROR_PROJECT_MODULE_MAP.put(IError, projectModule);
    }

    public static List<TreeNode> getAllErrorCodes() {
        return ERROR_PROJECT_MODULE_MAP.entrySet().stream()
                .sorted((it1, it2) -> ERROR_CODE_COMPARATOR.compare(it1.getKey(), it2.getKey()))
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet()
                .stream()
                .sorted((it1, it2) -> PROJECT_MODULE_COMPARATOR.compare(it1.getKey(), it2.getKey()))
                .collect(Collectors.groupingBy(
                                e -> new TreeNode(e.getKey().getProjectCode(), e.getKey().getProjectName()),
                                Collectors.groupingBy(
                                        it -> new TreeNode(it.getKey().getModuleCode(), it.getKey().getModuleName()),
                                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                                )
                        )
                )
                .entrySet()
                .stream()
                .map(e -> {
                    TreeNode top = e.getKey();
                    List<TreeNode> middleNode = e.getValue()
                            .entrySet()
                            .stream()
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

    private static int genCode(ProjectModule projectModule, IError IError) {
        return projectModule.getProjectCode() * 10000 + projectModule.getModuleCode() * 100 + IError.getNodeNum();
    }

    public static int genCode(IError IError) {
        return GLOBAL_ERROR_CODE_MAP.inverse().get(IError);
    }

    public static ProjectModule projectModule(IError IError) {
        return ERROR_PROJECT_MODULE_MAP.get(IError);
    }
}
