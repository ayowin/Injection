package com.wz.injection.cyclic;

import com.wz.injection.Autowired;
import com.wz.injection.Inject;

@Inject
public class CyclicDependenceClassA {

    @Autowired
    CyclicDependenceClassB cyclicDependenceClassB;

}
