package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.IfStmnt;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.ExecutorFactory;
import com.flypig.stone.execute.IExecutor;

public class IfStmntExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {

        IfStmnt stmnt = (IfStmnt)asTree;
        Object result = ExecutorFactory.getInstance().execute(stmnt.condition(), context);
        if(!(result instanceof Boolean)){
            throw new RuntimeException("if condition need boolean "+stmnt.condition().toString());
        }

        Object ret = null;
        if((Boolean) result){
            ret = ExecutorFactory.getInstance().execute(stmnt.thenBlock(), context);
        }else{
            if(stmnt.elseBlock() != null){
                ret = ExecutorFactory.getInstance().execute(stmnt.elseBlock(), context);
            }
        }
        return ret;
    }
}
