package com.flypig.stone.ast;

import java.util.List;

public class IfStmnt extends ASTList{
    public IfStmnt(List<ASTree> children) {
        super(children);
    }

    public ASTree condition(){
        return getChild(0);
    }

    public ASTree thenBlock(){
        return getChild(1);
    }

    public ASTree elseBlock(){
        if(getChildCount() > 2){
            return getChild(2);
        }
        return null;
    }

    @Override
    public String toString() {
        return "(if "+condition() +" "+thenBlock()+" else "+ elseBlock()+")";
    }
}
