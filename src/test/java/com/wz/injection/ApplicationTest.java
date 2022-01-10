package com.wz.injection;

import com.wz.injection.controller.TestController;
import org.junit.Test;

public class ApplicationTest {

    @Test
    public void test(){
        Injection.scanAnnotation(ApplicationTest.class.getPackage().getName());

        TestController testController = (TestController) InjectContainer.get("testController");
        String response = testController.test();
        System.out.println(response);
    }

}
