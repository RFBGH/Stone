package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.Name;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.IExecutor;

public class NameExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {
        Name name = (Name) asTree;
        Object result = context.getInt(name.getName());
        if(result == null){
            result = context.getString(name.getName());
        }

        if(result == null){
            throw new RuntimeException("result is null "+name.toString());
        }

        return result;
    }
}
