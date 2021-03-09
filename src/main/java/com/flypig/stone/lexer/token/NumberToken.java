package com.flypig.stone.lexer.token;

public class NumberToken extends Token{
    private int value;
    public NumberToken(int lineNumber, int value) {
        super(lineNumber);
        this.value = value;
    }

    @Override
    public int getLineNumber() {
        return value;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public String getText() {
        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return "NumberToken{" +
                "value=" + value +
                '}';
    }
}
