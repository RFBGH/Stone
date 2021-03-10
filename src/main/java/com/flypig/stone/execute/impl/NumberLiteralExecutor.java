package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.NumberLiteral;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.IExecutor;

public class NumberLiteralExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {
        NumberLiteral numberLiteral = (NumberLiteral)asTree;
        return numberLiteral.getValue();
    }
}
