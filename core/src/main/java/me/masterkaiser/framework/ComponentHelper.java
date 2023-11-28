package me.masterkaiser.framework;

import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ComponentHelper {
    public static String[] getScans(Class<?> clazz) {
        final List<String> packages = new ArrayList<>();
        final ComponentScan componentScan = clazz.getAnnotation(ComponentScan.class);
        final ComponentScans componentScans = clazz.getAnnotation(ComponentScans.class);

        if (componentScan == null && componentScans == null) {
            throw new MasterAppException("The main class must be tagged with annotation ComponentScan or ComponentScans");
        }

        if (componentScan != null) {
            packages.addAll(List.of(componentScan.basePackages()));
        }

        if (componentScans != null) {
            Arrays.stream(componentScans.value()).forEach(cs -> {
                packages.addAll(List.of(cs.basePackages()));
            });
        }

        return packages.toArray(new String[0]);
    }
}
