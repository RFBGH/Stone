package com.flypig.stone.lexer.token;

public class StringToken extends Token{
    private String value;
    public StringToken(int lineNumber, String value) {
        super(lineNumber);
        this.value = value;
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public String getText() {
        return value;
    }

    @Override
    public String toString() {
        return "StringToken{" +
                "value='" + value + '\'' +
                '}';
    }
}
