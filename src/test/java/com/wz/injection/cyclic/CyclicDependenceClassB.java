package com.wz.injection.cyclic;

import com.wz.injection.Autowired;
import com.wz.injection.Inject;

@Inject
public class CyclicDependenceClassB {

    @Autowired
    CyclicDependenceClassA cyclicDependenceClassA;

}
