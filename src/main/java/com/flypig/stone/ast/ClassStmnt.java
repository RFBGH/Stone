package com.flypig.stone.ast;

import java.util.List;

public class ClassStmnt extends ASTList{

    public ClassStmnt(List<ASTree> children) {
        super(children);
    }

    public String getName(){
        return ((ASTLeaf)getChild(0)).getToken().getText();
    }

    public String getParent(){
        ASTree asTree = getChild(1);
        if(asTree instanceof ClassBody){
            return null;
        }
        return ((ASTLeaf)asTree).getToken().getText();
    }

    public ClassBody getClassBody(){
        ASTree asTree = getChild(1);
        if(asTree instanceof ClassBody){
            return (ClassBody)asTree;
        }

        asTree = getChild(2);
        return (ClassBody)asTree;
    }

    @Override
    public String toString() {
        return "(class "+getName()+(getParent()==null?"":" extends "+getParent())+getClassBody()+")";
    }
}
