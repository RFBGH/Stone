package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.PrimaryExpr;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.ExecutorFactory;
import com.flypig.stone.execute.IExecutor;

public class PrimaryExprExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {

        PrimaryExpr primaryExpr = (PrimaryExpr)asTree;
        Object last = null;
        for(ASTree child:primaryExpr){
            last = ExecutorFactory.getInstance().execute(child, context);
        }
        return last;
    }
}
