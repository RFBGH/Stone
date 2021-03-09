package com.flypig.stone.ast;

import java.util.Iterator;
import java.util.List;

public class ASTList extends ASTree{

    protected List<ASTree> children;

    public ASTList(List<ASTree> children){
        this.children = children;
    }

    @Override
    public Iterator<ASTree> getChildren() {
        return children.iterator();
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public ASTree getChild(int i) {
        return children.get(i);
    }

    @Override
    public String location() {
        for(ASTree child : children){
            String loc = child.location();
            if(loc != null){
                return loc;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for(ASTree child : children){
            sb.append(child.toString());
            sb.append(" ");
        }
        sb.append(")");
        return sb.toString();
    }
}
