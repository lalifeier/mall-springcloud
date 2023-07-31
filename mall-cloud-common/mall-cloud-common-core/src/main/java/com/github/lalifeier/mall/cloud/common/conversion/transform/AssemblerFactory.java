// package com.github.lalifeier.conversion.transform;
//
// import org.springframework.util.ReflectionUtils;
//
// public class AssemblerFactory {
//    private static AssemblerFactory INSTANCE = new AssemblerFactory();
//
//    private AssemblerFactory() {
//    }
//
//    public static AssemblerFactory getInstance() {
//        return INSTANCE;
//    }
//
//    private Assembler getAssembler(Class type) {
//        Assembler assembler = null;
//        try {
//            assembler = (Assembler) ReflectionUtils.accessibleConstructor(type, new
// Class[0]).newInstance(new Object[0]);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return assembler;
//    }
//
//    public <R, T> T execute(Class type, R original, Class<T> targetType) {
//        Assembler assembler = getAssembler(type);
//        T target = (T) assembler.convert(original, targetType);
//        return target;
//    }
// }
