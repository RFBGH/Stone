package com.flypig.stone.ast;

import com.flypig.stone.lexer.token.Token;

public class Name extends ASTLeaf{
    public Name(Token token) {
        super(token);
    }

    public String getName(){
        return token.getText();
    }
}
