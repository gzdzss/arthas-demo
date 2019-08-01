# Arthas Demo

- [Github](https://github.com/alibaba/arthas) 
- [文档](https://alibaba.github.io/arthas/)
- [教程](https://alibaba.github.io/arthas/arthas-tutorials?language=cn&id=arthas-advanced)

# 热更新代码

## 准备


### 1.启动服务
 
com.gzdzss.arthas.demo.SayHello

### 2.下载arthas-boot.jar
```shell
wget https://alibaba.github.io/arthas/arthas-boot.jar
java -jar arthas-boot.jar --target-ip 0.0.0.0
```
### 3.选择对应的服务
 [1]: 10300 com.gzdzss.arthas.demo.SayHello
 
 
### 4.查找类
```shell
$ sc *SayHello
com.gzdzss.arthas.demo.SayHello
Affect(row-cnt:1) cost in 10 ms.
```
### 5 反编译
```shell
jad  com.gzdzss.arthas.demo.SayHello

## 通过--source-only参数可以只打印出在反编译的源代码：
## 这里我们把类
jad --source-only  com.gzdzss.arthas.demo.SayHello > f:SayHello.java
```


### 6 修改SayHello.java 输出为 hello arthas
```java
/*
 * Decompiled with CFR 0_132.
 */
package com.gzdzss.arthas.demo;

import java.io.PrintStream;

public class SayHello {
    public static void main(String[] args) throws InterruptedException {
        do {
            Thread.sleep(5000L);
            new Thread(() -> System.out.println("hello arthas")).start();
        } while (true);
    }
}

```

### 7  查找类加载
```shell 
sc -d *SayHello | grep classLoaderHash
```

### 8  mc
```shell 
mc -c   18b4aac2  f:SayHello.java -d f:
```

### 9 redefine
```shell 
redefine  f:/com/gzdzss/arthas/demo/SayHello.class
```


### 其他

- Ognl
    - 在Arthas里，有一个单独的ognl命令，可以动态执行代码。
    
```shell
  #调用static函数
  ognl '@java.lang.System@out.println("hello ognl")'
```
  