package com.flypig.stone.execute.impl;

import com.flypig.stone.StoneConst;
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
            case StoneConst.EQUAL:
                return dealEqual(left, right, context);

            case StoneConst.CHECK_EQUAL:
                return dealCheckEqual(left, right, context);

            case StoneConst.BIGGER:
                return dealBigger(left, right, context);

            case StoneConst.SMALLER:
                return dealSmaller(left, right, context);

            case StoneConst.ADD:
                return dealAdd(left, right, context);

            case StoneConst.SUB:
                return dealSub(left, right, context);

            case StoneConst.MULT:
                return dealMult(left, right, context);

            case StoneConst.DIVID:
                return dealDivid(left, right, context);

            case StoneConst.MOD:
                return dealMod(left, right, context);
        }

        throw new RuntimeException("unsupport op "+binaryExpr.toString());
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

    private Object dealCheckEqual(ASTree left, ASTree right, Context context){
        Object leftValue = ExecutorFactory.getInstance().execute(left, context);
        Object rightValue = ExecutorFactory.getInstance().execute(right, context);

        if(leftValue == null && rightValue == null){
            return true;
        }

        if(leftValue == null){
            return false;
        }

        return leftValue.equals(rightValue);
    }

    private int getIntValue(ASTree tree, Context context){
        Object value = ExecutorFactory.getInstance().execute(tree, context);
        if(!(value instanceof Integer)){
            throw new RuntimeException("tree is not int "+tree.toString());
        }
        return (int)value;
    }

    private Object dealBigger(ASTree left, ASTree right, Context context){
        int leftValue = getIntValue(left, context);
        int rightValue = getIntValue(right, context);
        return leftValue > rightValue;
    }

    private Object dealSmaller(ASTree left, ASTree right, Context context){
        int leftValue = getIntValue(left, context);
        int rightValue = getIntValue(right, context);
        return leftValue < rightValue;
    }

    private Object dealAdd(ASTree left, ASTree right, Context context){
        int leftValue = getIntValue(left, context);
        int rightValue = getIntValue(right, context);
        return leftValue + rightValue;
    }

    private Object dealSub(ASTree left, ASTree right, Context context){
        int leftValue = getIntValue(left, context);
        int rightValue = getIntValue(right, context);
        return leftValue - rightValue;
    }

    private Object dealMult(ASTree left, ASTree right, Context context){
        int leftValue = getIntValue(left, context);
        int rightValue = getIntValue(right, context);
        return leftValue * rightValue;
    }

    private Object dealDivid(ASTree left, ASTree right, Context context){
        int leftValue = getIntValue(left, context);
        int rightValue = getIntValue(right, context);

        if(rightValue == 0){
            throw new RuntimeException("divid by zero "+right.toString());
        }
        return leftValue / rightValue;
    }

    private Object dealMod(ASTree left, ASTree right, Context context){
        int leftValue = getIntValue(left, context);
        int rightValue = getIntValue(right, context);

        if(rightValue == 0){
            throw new RuntimeException("mod by zero "+right.toString());
        }
        return leftValue % rightValue;
    }
}
