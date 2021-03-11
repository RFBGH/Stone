package com.flypig.stone.execute;

public class Variable {

    private Object value;

    public void setObject(Object value){
        this.value = value;
    }

    public Object getObject(){
        return value;
    }

    public String getString(){
        if(value == null){
            return null;
        }
        return value.toString();
    }

    public int getInt(){
        return (int)value;
    }

    public boolean isNumber(){
        return value instanceof Integer;
    }

    public boolean isString(){
        return value instanceof String;
    }

    @Override
    public String toString() {
        if(value == null){
            return "null";
        }
        return value.toString();
    }
}
