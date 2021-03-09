package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.lexer.token.Token;

import java.util.List;

public class Skip extends Leaf{
    protected Skip(String[] pat) {
        super(pat);
    }

    @Override
    protected void find(List<ASTree> res, Token t) { }
}
