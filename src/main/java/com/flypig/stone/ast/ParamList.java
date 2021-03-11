package com.flypig.stone.ast;


import java.util.List;

public class ParamList extends ASTList{
    public ParamList(List<ASTree> children) {
        super(children);
    }

    public String getName(int i){
        return ((ASTLeaf)getChild(i)).getToken().getText();
    }

    public int getSize(){
        return getChildCount();
    }
}
