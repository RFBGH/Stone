package com.flypig.stone.ast;

import java.util.List;

public class WhileStmnt extends ASTList{
    public WhileStmnt(List<ASTree> children) {
        super(children);
    }

    public ASTree condition(){
        return getChild(0);
    }

    public ASTree body(){
        return getChild(1);
    }

    @Override
    public String toString() {
        return "(while "+condition()+" "+body()+" )";
    }
}
