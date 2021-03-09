package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTLeaf;
import com.flypig.stone.lexer.token.Token;

public class StrToken extends AToken{
    protected StrToken(Class<? extends ASTLeaf> clazz) {
        super(clazz);
    }

    @Override
    protected boolean test(Token t) {
        return t.isString();
    }
}
