package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.*;
import com.flypig.stone.execute.*;
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
        if(func != null){
            return hitDef((Args) primaryExpr.getChild(1), context, func);
        }

        ASTree dot = primaryExpr.getChild(1);
        if(dot instanceof Dot){
            return hidDot(primaryExpr, context, (Dot)dot);
        }

       return null;
    }

    private Object hidDot(PrimaryExpr primaryExpr, Context context, Dot dot){
        String name = ((ASTLeaf)primaryExpr.getChild(0)).getToken().getText();

        ClassStmnt classStmnt = context.getClass(name);
        if(classStmnt != null){
            if(((ASTLeaf)dot.getMember()).getToken().getText().equals("new")){

                ClassBody classBody = classStmnt.getClassBody();
                Context nestContext = new Context(context);
                for(ASTree child:classBody){
                    if(child instanceof DefStmnt){
                        nestContext.putFunc((DefStmnt)child);
                    }else{
                        ExecutorFactory.getInstance().execute(child, nestContext);
                    }
                }

                String parent = classStmnt.getParent();
                if(parent != null){
                    ClassStmnt parentClassStmnt = context.getClass(parent);
                    ClassBody parentClassBody = parentClassStmnt.getClassBody();
                    for(ASTree child:parentClassBody){
                        if(child instanceof DefStmnt){
                            if (nestContext.getFunc(((DefStmnt) child).getName()) == null) {
                                nestContext.putFunc((DefStmnt)child);
                            }
                        }else{
                            ExecutorFactory.getInstance().execute(child, nestContext);
                        }
                    }
                }

                return new ClassObject(name, nestContext);
            }else{
                throw new RuntimeException("need new here "+primaryExpr.toString());
            }
        }

        Variable variable = context.get(name);
        if(!(variable.getObject() instanceof ClassObject)){
            throw new RuntimeException("need class Object here "+primaryExpr.toString());
        }

        ClassObject classObject = (ClassObject)variable.getObject();
        String dotName = ((ASTLeaf)dot.getMember()).getToken().getText();
        if(classObject.getContext().get(dotName) != null){
            return classObject.getContext().get(dotName);
        }

        DefStmnt func = classObject.getContext().getFunc(dotName);
        if(func != null){
            return hitDef((Args) primaryExpr.getChild(2), classObject.getContext(), func);
        }

        return null;
    }

    private Object hitDef(Args args, Context context, DefStmnt func){
        Context cusomContext = new Context(context.getParent() == null?context:context.getParent());
        cusomContext.setFunc(context.getFunc());

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
