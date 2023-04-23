package com.litongjava.host.swap.classloader.example;

import com.litongjava.hotswap.wrapper.app.SimpleApp;

/**
 * @author litongjava@qq.com on 2023/4/23 10:16
 */
public class HelloWorld {
  public static void main(String[] args){
    SimpleApp.run(HelloWorld.class.getName(),"index");
  }
  public void index(){
    System.out.println("Hello World 2");
  }
}
