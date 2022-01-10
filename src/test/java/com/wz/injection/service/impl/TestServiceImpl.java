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
