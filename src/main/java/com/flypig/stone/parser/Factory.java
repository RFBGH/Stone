package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTList;
import com.flypig.stone.ast.ASTree;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public abstract class Factory {

    private static final String factoryName = "create";

    protected abstract ASTree make0(Object arg) throws Exception;

    protected ASTree make(Object arg){
        try{
            return make0(arg);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Factory getForASTList(Class<? extends ASTree> clazz){

        Factory f = get(clazz, List.class);
        if(f != null){
            return f;
        }

        f = new Factory() {
            @Override
            protected ASTree make0(Object arg) throws Exception {
                List<ASTree> results = (List<ASTree>) arg;
                if(results.size() == 1){
                    return results.get(0);
                }else{
                    return new ASTList(results);
                }
            }
        };
        return f;
    }

    public static Factory get(Class<? extends ASTree> clazz, Class<?> argType){

        if(clazz == null){
            return null;
        }

        try {
            final Method m = clazz.getMethod(factoryName, new Class<?>[]{argType});
            return new Factory() {
                @Override
                protected ASTree make0(Object arg) throws Exception {
                    return (ASTree)m.invoke(null, arg);
                }
            };
        }catch (Throwable e){
//            e.printStackTrace();
        }

        try{
            final Constructor<? extends ASTree> c = clazz.getConstructor(argType);
            return new Factory() {
                @Override
                protected ASTree make0(Object arg) throws Exception {
                    return c.newInstance(arg);
                }
            };
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
