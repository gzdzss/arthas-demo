package com.gzdzss.arthas.demo;

/**
 * @author <a href="mailto:zhouyanjie666666@gmail.com">zyj</a>
 * @date 2019/8/1
 */

public class SayHello {

    public static void main(String[] args) throws InterruptedException {
        while(true) {
            Thread.sleep(5000);
            new Thread(()-> {
                System.out.println("hello gzdzss");
            }).start();
        }
    }
}
