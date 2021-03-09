package com.flypig.stone.lexer.token;

import com.flypig.stone.exception.StoneException;

public abstract class Token {

    public static final Token EOF = new Token(-1) {};
    public static final String EOL = "\\n";

    private int lineNumber = 0;
    public Token(int lineNumber){
        this.lineNumber =  lineNumber;
    }

    public boolean isNumber(){
        return false;
    }

    public boolean isString(){
        return false;
    }

    public boolean isIdentifier(){
        return false;
    }

    public String getText(){
        return "";
    }

    public int getNumber(){
        throw new StoneException("token is not number");
    }

    public int getLineNumber(){
        return lineNumber;
    }

}
