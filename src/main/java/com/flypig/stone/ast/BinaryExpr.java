package com.flypig.stone.ast;

import java.util.List;

public class BinaryExpr extends ASTList{
    public BinaryExpr(List<ASTree> children) {
        super(children);
    }

    public ASTree left(){
        return children.get(0);
    }

    public ASTree right(){
        return children.get(2);
    }

    public String operator(){
        return ((ASTLeaf)children.get(1)).token.getText();
    }
}
