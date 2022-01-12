[toc]



# Injection

一个轻量级Java依赖注入实现



## 1. 技术要点

* 反射机制
* 注解



## 2. 搭建指南

* 纯maven工程，按maven搭建即可



## 3. 使用指南

**示例：参考test示例，模拟了一个web工程的结构，由配置类（TestConfig）和业务组件（Controller、Service、ServiceImpl、Mapper）组成。**   

### 3.1 由配置类为容器实例化对象
**模拟在TestConfig中创建数据源对象中创建数据源对象、使用数据源获取连接对象的配置过程。**  

**TestConfig.java如下：**
```java
package com.wz.injection.config;

import com.wz.injection.Configuration;
import com.wz.injection.Inject;

@Configuration
public class TestConfig {

    public static class DataSource{

    }

    private static class Connection{
        private DataSource dataSource;
        public Connection(DataSource dataSource){
            this.dataSource = dataSource;
        }
    }

    @Inject
    public DataSource dataSource(){
        DataSource dataSource = new DataSource();
        System.out.println(dataSource);
        return dataSource;
    }

    @Inject
    public Connection connection(DataSource dataSource){
        Connection connection = new Connection(dataSource);
        System.out.println(connection);
        return connection;
    }

}
```
* @Configuration: 注明该类为配置类，将调用该类下的 **@Inject** 方法实例化对象。  
* @Inject：该方法实例化的对象会交由容器托管，有参数时，方法的参数由容器自动注入。  

### 3.2 类的实例化托管、成员变量自动注入  
**模拟Controller、Service、ServiceImpl、Mapper之间的依赖层次关系。**  

**TestController.java如下：**
```java
package com.wz.injection.controller;

import com.wz.injection.Autowired;
import com.wz.injection.Inject;
import com.wz.injection.service.TestService;

@Inject
public class TestController {

    @Autowired
    private TestService testService;

    public String test(){
        return testService.service();
    }

}
```

**TestService.java如下：**
```java
package com.wz.injection.service;

public interface TestService {
    String service();
}
```

**TestServiceImpl.java如下：**
```java
package com.wz.injection.service.impl;

import com.wz.injection.Autowired;
import com.wz.injection.Inject;
import com.wz.injection.mapper.TestMapper;
import com.wz.injection.service.TestService;

@Inject
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String service() {
        return testMapper.select();
    }
}
```

**TestMapper.java如下：**
```java
package com.wz.injection.mapper;

import com.wz.injection.Inject;

@Inject
public class TestMapper {
    private class Test{
        private int id;
        private String result;

        public Test(int id,String result){
            this.id = id;
            this.result = result;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "id=" + id +
                    ", result='" + result + '\'' +
                    '}';
        }
    }

    public String select(){
        Test test = new Test(1,"select sucess");
        return test.toString();
    }
}
```

* @Inject：将该类实例化并交由容器托管
* @Autowired: 由容器自动注入该对象的实例