package com.flypig.stone.execute.impl;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.StringLiteral;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.IExecutor;

public class StringLiteralExecutor implements IExecutor {
    @Override
    public Object execute(ASTree asTree, Context context) {
        StringLiteral string = (StringLiteral)asTree;
        return string.value();
    }
}
