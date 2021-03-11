package com.flypig.stone.ast;

import java.util.List;

public class Args extends ASTList{
    public Args(List<ASTree> children) {
        super(children);
    }

    public int getSize(){
        return getChildCount();
    }
}
