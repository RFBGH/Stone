package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.*;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.ExecutorFactory;
import com.flypig.stone.execute.IExecutor;
import com.flypig.stone.execute.Variable;

public class PrimaryExprExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {

        PrimaryExpr primaryExpr = (PrimaryExpr)asTree;

        String name = ((ASTLeaf)primaryExpr.getChild(0)).getToken().getText();
        DefStmnt func = context.getFunc(name);

        Context cusomContext = new Context(context.getParent() == null?context:context.getParent());
        cusomContext.setFunc(context.getFunc());

        Args args = (Args) primaryExpr.getChild(1);
        ParamList paramList = func.getParamList();
        for(int i = 0; i < args.getSize(); i++){
            ASTree arg = args.getChild(i);
            Object result = ExecutorFactory.getInstance().execute(arg, context);
            ASTLeaf param = (ASTLeaf)paramList.getChild(i);
            cusomContext.set(param.getToken().getText(), new Variable(result));
        }

        return ExecutorFactory.getInstance().execute(func.getBody(), cusomContext);
    }
}
