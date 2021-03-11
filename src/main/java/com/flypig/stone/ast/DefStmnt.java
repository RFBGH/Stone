package com.flypig.stone.ast;


import java.util.List;

public class DefStmnt extends ASTList{

    public DefStmnt(List<ASTree> children) {
        super(children);
    }

    public String getName(){
        return ((ASTLeaf)getChild(0)).getToken().getText();
    }

    public ParamList getParamList(){
        return (ParamList) getChild(1);
    }

    public BlockStmnt getBody(){
        return (BlockStmnt)getChild(2);
    }

    @Override
    public String toString() {
        return "def "+getName()+" "+getParamList().toString()+" "+getBody();
    }
}
