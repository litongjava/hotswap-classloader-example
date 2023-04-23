package com.litongjava.host.swap.classloader.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.litongjava.hotswap.debug.Diagnostic;
import com.litongjava.hotswap.kit.HotSwapUtils;
import com.litongjava.hotswap.wrapper.app.SimpleApp;

public class SimpleAppExample {

  public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
    new SimpleAppExample().test3();
  }

  public void test1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    // 使用HotSwapClassLoader加载类
    ClassLoader hotSwapClassLoader = HotSwapUtils.getClassLoader();
    Class<?> clazz = hotSwapClassLoader.loadClass("com.litongjava.host.swap.classloader.example.IndexObject");
    // 创建实例并调用方法
    System.out.println(IndexObject.class.getClassLoader());
    System.out.println(clazz.getClassLoader());
    IndexObject indexObject = (IndexObject) clazz.newInstance();
//    System.out.println(indexObject.getClass().getClassLoader());
    indexObject.index();
  }

  public void test2() {
    ClassLoader hotSwapClassLoader = HotSwapUtils.getClassLoader();
    // 第一次启动不需要使用自定义的类加载器,使用默认的类加载器即可
    Thread.currentThread().setContextClassLoader(hotSwapClassLoader);

    IndexObject indexObject = new IndexObject();
    System.out.println(indexObject.getClass().getClassLoader());
  }

  public void test4() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
    ClassLoader classLoader = HotSwapUtils.getClassLoader();
    Class<?> clazz = classLoader.loadClass("com.litongjava.host.swap.classloader.example.IndexObject");
    Object indexObject = clazz.newInstance();
    Method method = clazz.getDeclaredMethod("index");
    method.setAccessible(true);
    method.invoke(indexObject);
    //success
  }
  public void test5() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
    ClassLoader classLoader = HotSwapUtils.getClassLoader();
    String name = IndexObject.class.getName();
    System.out.println(name);
    Class<?> clazz = classLoader.loadClass(name);
    Object indexObject = clazz.newInstance();
    Method method = clazz.getDeclaredMethod("index");
    method.setAccessible(true);
    method.invoke(indexObject);
    //success
  }

  public void test3() {
    Diagnostic.setDebug(true);
    SimpleApp.run(IndexObject.class.getName(),"index");
  }
}