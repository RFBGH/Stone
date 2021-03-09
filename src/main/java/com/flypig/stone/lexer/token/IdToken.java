package com.flypig.stone.lexer.token;

public class IdToken extends Token{

    private String value;
    public IdToken(int lineNumber, String id) {
        super(lineNumber);
        this.value = id;
    }

    @Override
    public boolean isIdentifier() {
        return true;
    }

    @Override
    public String getText() {
        return value;
    }

    @Override
    public String toString() {
        return "IdToken{" +
                "value='" + value + '\'' +
                '}';
    }
}
