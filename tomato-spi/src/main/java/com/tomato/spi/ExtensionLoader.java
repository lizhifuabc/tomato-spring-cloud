package com.tomato.spi;


import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * dubbo 自定义扩展机制
 * 从文件中读取配置，并通过反射实例化对象
 *
 * @param <T> the type parameter，参数类型
 * @author lizhifu
 * @date 2022/8/11
 */
@Slf4j
public final class ExtensionLoader<T> {
    /**
     * SPI 配置文件根目录
     */
    private static final String SERVICE_DIRECTORY = "META-INF/extend/";
    /**
     * 本地缓存，会先通过getExtensionLoader方法从缓存中获取一个ExtensionLoader
     * 若缓存未命中，则会生成一个新的实例
     */
    private static final Map<Class<?>, ExtensionLoader<?>> LOADERS = new ConcurrentHashMap<>();
    /**
     * 需要加载的扩展类类别
     */
    private final Class<T> clazz;
    /**
     * 类加载器
     */
    private final ClassLoader classLoader;
    /**
     * 扩展类实例对象，key为配置文件中的key，value为实例对象的全限定名称
     */
    private final Holder<Map<String, Class<?>>> cachedClasses = new Holder<>();
    /**
     * 目标扩展类的字节码和实例对象的映射关系
     */
    private final Map<String, Holder<Object>> cachedInstances = new ConcurrentHashMap<>();

    private final Map<Class<?>, Object> joinInstances = new ConcurrentHashMap<>();

    private String cachedDefaultName;
    /**
     * Instantiates a new Extension loader.
     *
     * @param clazz the clazz.
     */
    private ExtensionLoader(final Class<T> clazz, final ClassLoader cl) {
        this.clazz = clazz;
        this.classLoader = cl;
        if (!Objects.equals(clazz, ExtensionFactory.class)) {
           // ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getExtensionClasses();
        }
    }
    /**
     * 得到扩展加载程序
     *
     * @param <T>   the type parameter
     * @param clazz 扩展的接口，必须被 Spi 标记
     * @param cl    the cl
     * @return the extension loader.
     */
    public static <T> ExtensionLoader<T> getExtensionLoader(final Class<T> clazz, final ClassLoader cl) {
        // 判断是否为null
        Objects.requireNonNull(clazz, "extension clazz is null");
        // 如果不是接口，抛出异常
        if (!clazz.isInterface()) {
            throw new IllegalArgumentException("extension clazz (" + clazz + ") is not interface!");
        }
        // 判断是否被 Spi 标记
        if (!clazz.isAnnotationPresent(SPI.class)) {
            throw new IllegalArgumentException("extension clazz (" + clazz + ") without @" + SPI.class + " Annotation");
        }
        //先从缓存中获取扩展加载器，如果未命中，则创建一个新的扩展加载器
        ExtensionLoader<T> extensionLoader = (ExtensionLoader<T>) LOADERS.get(clazz);
        if (Objects.nonNull(extensionLoader)) {
            return extensionLoader;
        }
        //未命中则创建，并放入缓存
        LOADERS.putIfAbsent(clazz, new ExtensionLoader<>(clazz, cl));
        return (ExtensionLoader<T>) LOADERS.get(clazz);
    }

    /**
     * Gets extension loader.
     *
     * @param <T>   the type parameter
     * @param clazz the clazz
     * @return the extension loader
     */
    public static <T> ExtensionLoader<T> getExtensionLoader(final Class<T> clazz) {
        return getExtensionLoader(clazz, ExtensionLoader.class.getClassLoader());
    }

    /**
     * 加载扩展类
     * @param classes
     * @param name
     * @param classPath
     * @throws ClassNotFoundException
     */
    private void loadClass(final Map<String, Class<?>> classes,
                           final String name, final String classPath) throws ClassNotFoundException {
        Class<?> subClass = Objects.nonNull(this.classLoader) ? Class.forName(classPath, true, this.classLoader) : Class.forName(classPath);
        // 检查当前实现类是否实现了 type 接口
        if (!clazz.isAssignableFrom(subClass)) {
            throw new IllegalStateException("load extension resources error," + subClass + " subtype is not of " + clazz);
        }
        if (!subClass.isAnnotationPresent(Join.class)) {
            throw new IllegalStateException("load extension resources error," + subClass + " without @" + Join.class + " annotation");
        }
        Class<?> oldClass = classes.get(name);
        if (Objects.isNull(oldClass)) {
            classes.put(name, subClass);
        } else if (!Objects.equals(oldClass, subClass)) {
            throw new IllegalStateException("load extension resources error,Duplicate class " + clazz.getName() + " name " + name + " on " + oldClass.getName() + " or " + subClass.getName());
        }
    }
    /**
     * 通过ExtensionLoader加载来的实例化对象的对象
     *
     * @param <T> the type parameter. 参数类型
     */
    public static class Holder<T> {

        private volatile T value;

        /**
         * Gets value.
         *
         * @return the value
         */
        public T getValue() {
            return value;
        }

        /**
         * Sets value.
         *
         * @param value the value
         */
        public void setValue(final T value) {
            this.value = value;
        }
    }
}
