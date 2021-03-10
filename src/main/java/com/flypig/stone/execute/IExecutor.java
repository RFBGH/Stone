package com.flypig.stone.execute;

import com.flypig.stone.ast.ASTree;

public interface IExecutor {

    Object execute(ASTree asTree, Context context);

}
