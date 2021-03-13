package com.flypig.stone.ast;

import java.util.List;

public class Dot extends ASTList{
    public Dot(List<ASTree> children) {
        super(children);
    }

    public ASTree getMember(){
        return getChild(0);
    }

    @Override
    public String toString() {
        return "(Dot "+"."+getMember()+")";
    }
}
