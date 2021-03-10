package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.BlockStmnt;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.ExecutorFactory;
import com.flypig.stone.execute.IExecutor;

public class BlockStmntExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {
        BlockStmnt blockStmnt = (BlockStmnt) asTree;
        Object last = null;
        for(ASTree child : blockStmnt){
            last = ExecutorFactory.getInstance().execute(child, context);
        }
        return last;
    }
}
