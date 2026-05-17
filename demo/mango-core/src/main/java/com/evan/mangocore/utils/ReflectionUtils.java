package com.evan.mangocore.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {

    @SuppressWarnings("unchecked")
    public static Object invoke(Object target, String methodName, Object... args) {
        try {
            Class<?> clazz = target.getClass();
            Method method = findMethod(clazz, methodName, args);
            method.setAccessible(true);
            return method.invoke(target, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("反射调用方法失败: " + methodName, e);
        }
    }

    private static Method findMethod(Class<?> clazz, String methodName, Object... args) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName)
                    && method.getParameterCount() == args.length) {
                return method;
            }
        }
        throw new RuntimeException("找不到方法: " + methodName);
    }
}