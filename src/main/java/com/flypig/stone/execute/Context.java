package com.flypig.stone.execute;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, Integer> intVariable = new HashMap<>();
    private Map<String, String> strVariable = new HashMap<>();

    private Context parent;
    public Context(Context parent){
        this.parent = parent;
    }

    public Integer getInt(String name){
        if(intVariable.containsKey(name)){
            return intVariable.get(name);
        }
        if(parent != null){
            return parent.getInt(name);
        }
        throw new RuntimeException("cant find int in "+name);
    }

    public void setInt(String name, int i){
        intVariable.put(name, i);
    }

    public String getString(String name){
        if(strVariable.containsKey(name)){
            return strVariable.get(name);
        }

        if(parent != null){
            return parent.getString(name);
        }

        throw new RuntimeException("cant find int in "+name);
    }

    public void setString(String name, String str){
        strVariable.put(name, str);
    }

}
