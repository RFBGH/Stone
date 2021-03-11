package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.Name;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.IExecutor;

public class NameExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {
        Name name = (Name) asTree;
        return context.get(name.getName()).getObject();
    }
}
