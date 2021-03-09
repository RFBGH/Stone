package com.flypig.stone.ast;

import java.util.List;

public class NegativeExpr extends ASTList{

    public NegativeExpr(List<ASTree> children) {
        super(children);
    }

    public ASTree operand(){
        return getChild(0);
    }

    @Override
    public String toString() {
        return "-"+operand();
    }
}
