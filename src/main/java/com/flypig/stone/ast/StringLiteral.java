package com.flypig.stone.ast;

import com.flypig.stone.lexer.token.Token;

public class StringLiteral extends ASTLeaf{
    public StringLiteral(Token token) {
        super(token);
    }

    public String value(){
        return token.getText();
    }
}
