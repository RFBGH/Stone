package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.*;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.ExecutorFactory;
import com.flypig.stone.execute.IExecutor;
import com.flypig.stone.execute.Variable;
import com.flypig.stone.execute.native_func.INativeFunc;
import com.flypig.stone.execute.native_func.NativeFun;

public class PrimaryExprExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {

        PrimaryExpr primaryExpr = (PrimaryExpr)asTree;

        String name = ((ASTLeaf)primaryExpr.getChild(0)).getToken().getText();
        if(NativeFun.getInstance().hitNative(name)){
            return invokeNative(primaryExpr, context);
        }

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

    private Object invokeNative(PrimaryExpr primaryExpr, Context context){
        String name = ((ASTLeaf)primaryExpr.getChild(0)).getToken().getText();
        Args args = (Args) primaryExpr.getChild(1);

        Object[] arguments = new Object[args.getSize()];
        for(int i = 0; i < args.getSize(); i++){
            ASTree arg = args.getChild(i);
            Object result = ExecutorFactory.getInstance().execute(arg, context);
            arguments[i] = result;
        }

        return NativeFun.getInstance().invoke(name, arguments);
    }
}
