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
