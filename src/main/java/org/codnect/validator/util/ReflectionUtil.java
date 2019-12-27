package org.codnect.validator.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class ReflectionUtil {

    private ReflectionUtil() {

    }

    public static boolean isStatic(Method method) {
        Method function = Optional.ofNullable(method)
                .orElseThrow(() -> new IllegalArgumentException("Method must not be null."));
        return Modifier.isStatic(function.getModifiers());
    }

    public static Set<Method> getAllStaticMethods(Class<?> zClass) {
        Class functionalClass = Optional.ofNullable(zClass)
                .orElseThrow(() -> new IllegalArgumentException("Class must not be null."));
        Method[] methods = functionalClass.getDeclaredMethods();
        return Arrays.stream(methods)
                .filter(ReflectionUtil::isStatic)
                .collect(Collectors.toSet());
    }

}

