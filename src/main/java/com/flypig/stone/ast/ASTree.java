package com.flypig.stone.ast;

import java.util.Iterator;
import java.util.List;

public abstract class ASTree implements Iterable<ASTree>{

    public abstract Iterator<ASTree> getChildren();
    public abstract int getChildCount();
    public abstract ASTree getChild(int i);
    public abstract String location();
    public Iterator<ASTree> iterator(){
        return getChildren();
    }
}
