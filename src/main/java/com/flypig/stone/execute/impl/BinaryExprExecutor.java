package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.BinaryExpr;
import com.flypig.stone.ast.Name;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.ExecutorFactory;
import com.flypig.stone.execute.IExecutor;
import com.flypig.stone.execute.Variable;

public class BinaryExprExecutor implements IExecutor {

    @Override
    public Object execute(ASTree asTree, Context context) {

        BinaryExpr binaryExpr = (BinaryExpr) asTree;
        ASTree left = binaryExpr.left();
        ASTree right = binaryExpr.right();

        switch (binaryExpr.operator()){
            case "=":
                return dealEqual(left, right, context);
        }
        return null;
    }

    private Object dealEqual(ASTree left, ASTree right, Context context){
        if(!(left instanceof Name)){
            throw new RuntimeException("= left need variable "+left.toString());
        }

        Object value = ExecutorFactory.getInstance().execute(right, context);
        Variable variable = context.getOrCreate(((Name) left).getName());
        variable.setObject(value);
        return value;
    }
}
