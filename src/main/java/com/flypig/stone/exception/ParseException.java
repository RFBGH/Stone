package com.flypig.stone.exception;

public class ParseException extends Exception{

    public ParseException(String msg){
        super(msg);
    }

    public ParseException(String msg, Exception parent){
        super(msg, parent);
    }
}
