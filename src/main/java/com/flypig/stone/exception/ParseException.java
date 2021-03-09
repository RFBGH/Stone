package com.flypig.stone.exception;

import com.flypig.stone.lexer.token.Token;

public class ParseException extends Exception{

    public ParseException(Token token){
        super(token.toString());
    }

    public ParseException(String msg, Token token){
        super(token.toString());
    }


    public ParseException(String msg){
        super(msg);
    }

    public ParseException(String msg, Exception parent){
        super(msg, parent);
    }
}
