package com.flypig.stone.execute.native_func;

import com.flypig.stone.execute.native_func.impl.PrintNativeFunc;

import java.util.HashMap;
import java.util.Map;

public class NativeFun {

    private static final NativeFun sInstance = new NativeFun();

    public static NativeFun getInstance() {
        return sInstance;
    }

    private Map<String, INativeFunc> nativeFuncMap = new HashMap<>();
    private NativeFun(){
        register(new PrintNativeFunc());
    }

    private void register(INativeFunc func){
        nativeFuncMap.put(func.getName(), func);
    }

    public boolean hitNative(String name){
        return nativeFuncMap.containsKey(name);
    }

    public Object invoke(String name, Object...args){
        INativeFunc func = nativeFuncMap.get(name);
        return func.invoke(args);
    }
}
