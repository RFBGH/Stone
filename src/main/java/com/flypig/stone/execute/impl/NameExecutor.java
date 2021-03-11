package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.Name;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.IExecutor;
import com.flypig.stone.execute.Variable;

public class NameExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {
        Name name = (Name) asTree;
        Variable variable = context.get(name.getName());
        if(variable == null){
            throw new RuntimeException("null in name "+asTree.toString());
        }
        return variable.getObject();
    }
}
