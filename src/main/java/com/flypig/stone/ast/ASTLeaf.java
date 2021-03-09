package com.flypig.stone.ast;

import com.flypig.stone.lexer.token.Token;

import java.util.Iterator;

public class ASTLeaf extends ASTree{

    protected Token token;
    public ASTLeaf(Token token){
        this.token = token;
    }

    @Override
    public Iterator<ASTree> getChildren() {
        throw new UnsupportedOperationException("leaf has no child");
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public ASTree getChild(int i) {
        throw new UnsupportedOperationException("leaf has no child");
    }

    @Override
    public String location() {
        return "at line "+token.getLineNumber();
    }

    @Override
    public String toString() {
        return token.getText();
    }

    public Token getToken() {
        return token;
    }
}
