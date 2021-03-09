package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTLeaf;
import com.flypig.stone.lexer.token.Token;

import java.util.Set;

public class IdToken extends AToken{

    Set<String> reserved;

    protected IdToken(Class<? extends ASTLeaf> clazz, Set<String> reserved) {
        super(clazz);
        this.reserved = reserved;
    }

    @Override
    protected boolean test(Token t) {
        return t.isIdentifier() && !reserved.contains(t.getText());
    }
}
