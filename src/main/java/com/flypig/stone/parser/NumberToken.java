package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTLeaf;
import com.flypig.stone.lexer.token.Token;

public class NumberToken extends AToken{
    protected NumberToken(Class<? extends ASTLeaf> clazz) {
        super(clazz);
    }

    @Override
    protected boolean test(Token t) {
        return t.isNumber();
    }
}
