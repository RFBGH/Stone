package com.flypig.stone.execute.native_func.impl;

import com.flypig.stone.execute.native_func.INativeFunc;

public class PrintNativeFunc implements INativeFunc {
    @Override
    public String getName() {
        return "print";
    }

    @Override
    public Object invoke(Object... args) {
        System.out.println("native "+args[0]);
        return null;
    }
}
