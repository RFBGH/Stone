package com.flypig.stone.execute.native_func;

public interface INativeFunc {

    String getName();

    Object invoke(Object...args);

}
