package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.NegativeExpr;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.ExecutorFactory;
import com.flypig.stone.execute.IExecutor;

public class NegativeExprExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {
        NegativeExpr expr = (NegativeExpr) asTree;
        int result = (int)ExecutorFactory.getInstance().execute(expr.operand(), context);
        return -result;
    }
}
