[toc]



# Injection

一个轻量级Java依赖注入实现



## 1. 技术要点

* 反射机制
* 注解



## 2. 搭建指南

* 纯maven工程，按maven搭建即可



## 3. 使用指南

**示例：参考test示例，模拟了一个web工程的结构，有Controller、Service、ServiceImpl、Mapper，各组件间存在依赖注入的关系。**

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

* @Inject：将该类的实例化并交由容器托管
* @Autowired: 由容器自动注入该对象的实例

