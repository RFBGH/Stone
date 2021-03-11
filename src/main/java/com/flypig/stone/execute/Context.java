package com.flypig.stone.execute;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, Variable> variable = new HashMap<>();

    private Context parent;
    public Context(Context parent){
        this.parent = parent;
    }

    public Variable get(String name){
        if(variable.containsKey(name)){
            return variable.get(name);
        }
        if(parent != null){
            return parent.get(name);
        }
        throw new RuntimeException("cant find int in "+name);
    }

    public Variable getOrCreate(String name){

        Variable var = variable.get(name);
        if(var == null){
            var = parent.get(name);
        }

        if(var == null){
            var = new Variable();
            variable.put(name, var);
        }
        return var;
    }

    public void set(String name, Variable value){
        variable.put(name, value);
    }
}
