package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.WhileStmnt;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.ExecutorFactory;
import com.flypig.stone.execute.IExecutor;

public class WhileStmntExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {
        WhileStmnt stmnt = (WhileStmnt)asTree;
        while (ExecutorFactory.getInstance().execute(stmnt.condition(), context) != null) {
            ExecutorFactory.getInstance().execute(stmnt.body(), context);
        }
        return null;
    }
}
