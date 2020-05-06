package com.travel.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 扫描加载class工具类
 *
 * @author soddy
 */
@Slf4j
public class ClassScanUtil {

    public static <T> T newInstance(Class<?> class_, Class<T> superClass) {
        try {
            return superClass.cast(class_.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("ClassScanUtil error", e);
        }
        return null;
    }

    /**
     * 过滤有注解的类
     *
     * @param class_ 注解
     * @return list
     */
    public static List<Class<?>> find(Class<? extends Annotation> class_) {
        final List<Class<?>> classes = new ArrayList<>();
        for (Class<?> cls : classes()) {
            if (cls.isAnnotationPresent(class_)) {
                classes.add(cls);
            }
        }
        return classes;
    }

    public static List<Class<?>> classes() {
        try {
            final Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources("com/dmall/rdp/voucher/");
            final List<Class<?>> classes = new ArrayList<>();
            while (urls.hasMoreElements()) {
                final URL url = urls.nextElement();
                final String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    classFromFileDir(new File(URLDecoder.decode(url.getFile(), "utf-8")), classes);
                } else if ("jar".equals(protocol)) {
                    final JarURLConnection jrc = (JarURLConnection) url.openConnection();
                    final JarFile jar = jrc.getJarFile();
                    final Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        final JarEntry entry = entries.nextElement();
                        final String path = entry.getName();
                        if (!entry.isDirectory() && path.endsWith(".class")) {
                            classes.add(getClassNameByPath(path));
                        }
                    }
                }
            }
            return classes;
        } catch (Exception e) {
            log.error("error", e);
        }
        return Collections.emptyList();
    }

    /**
     * 来自普通文件夹的类
     */
    public static void classFromFileDir(File file, List<Class<?>> classes) throws ClassNotFoundException {
        if (file.isDirectory()) {
            File[] files = file.listFiles(pathname -> pathname.getName().endsWith(".class") || pathname.isDirectory());
            if (files != null) {
                for (File readf : files) {
                    classFromFileDir(readf, classes);
                }
            }
        } else if (file.isFile() && file.getName().endsWith(".class")) {
            classes.add(getClassNameByPath(file.getPath()));
        }
    }

    public static Class<?> getClassNameByPath(String path) throws ClassNotFoundException {
        log.debug("path>>>>{}", path);

        String tempPath = path.replace("/", ".");
        final String className = tempPath.substring(tempPath.lastIndexOf("com."))
                .replace(".class", "")
                .replace("\\", ".");
        log.debug("className>>>>{}", className);
        return Thread.currentThread().getContextClassLoader().loadClass(className);
    }

    public static void main(String[] args) {
        String path = "/Users/soddy/Documents/git_work/dmall-rdp-voucher/rdp-voucher-api/target/classes/com/dmall/rdp/voucher/api/ServletInitializer.class";
        String className =
                path.replace("/", ".");

        className = className.substring(className.lastIndexOf("com.")).replace(".class", "").replace("\\", ".");
        log.debug("className>>>>{}", className);

    }
}
