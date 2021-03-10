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
        Object ret = null;
        while (true) {

            Object result = ExecutorFactory.getInstance().execute(stmnt.condition(), context);
            if(!(result instanceof Boolean)){
                throw new RuntimeException("while condition need boolean "+stmnt.condition().toString());
            }

            if(!((Boolean)result)){
                break;
            }

            ret = ExecutorFactory.getInstance().execute(stmnt.body(), context);
        }
        return ret;
    }
}
