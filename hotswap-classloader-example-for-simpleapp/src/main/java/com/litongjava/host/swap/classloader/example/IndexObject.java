package com.litongjava.host.swap.classloader.example;

import com.litongjava.hotswap.wrapper.app.SimpleApp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndexObject {
  public static void main(String[] args) {
    SimpleApp.run(IndexObject.class.getName(),"index");
  }
  
  public void index() {
    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    log.info("HotSwapClassLoader is set as context class loader: {}", contextClassLoader);
    ClassLoader classLoader2 = IndexObject.class.getClassLoader();
    log.info("class loader:{}", classLoader2);
    ClassLoader classLoader = this.getClass().getClassLoader();
    log.info("object class loader:{}", classLoader);
    System.out.println("Hello,World");
  }
}
