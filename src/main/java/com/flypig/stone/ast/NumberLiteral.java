package com.flypig.stone.ast;

import com.flypig.stone.lexer.token.Token;

public class NumberLiteral extends ASTLeaf{
    public NumberLiteral(Token token) {
        super(token);
    }

    public int getValue(){
        return token.getNumber();
    }
}
