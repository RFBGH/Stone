package com.flypig.stone.execute;

import com.flypig.stone.ast.DefStmnt;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, Variable> variable = new HashMap<>();
    private Map<String, DefStmnt> func = new HashMap<>();

    private Context parent;
    public Context(Context parent){
        this.parent = parent;
    }

    public Context getParent(){
        return parent;
    }

    public Variable get(String name){
        if(variable.containsKey(name)){
            return variable.get(name);
        }
        if(parent != null){
            return parent.get(name);
        }
        return null;
    }

    public Variable getOrCreate(String name){

        Variable var = variable.get(name);
        if(var == null){
            if(parent != null){
                var = parent.get(name);
            }
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

    public void setFunc(Map<String, DefStmnt> func){
        if(func == null){
            return;
        }
        this.func.putAll(func);
    }

    public Map<String, DefStmnt> getFunc(){
        return func;
    }

    public void putFunc(DefStmnt defStmnt){
        func.put(defStmnt.getName(), defStmnt);
    }

    public DefStmnt getFunc(String name){
        if(func.containsKey(name)){
            return func.get(name);
        }
        if(parent != null){
            return parent.getFunc(name);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Context{ \n");

        for(String key : variable.keySet()){
            sb.append(" ");
            sb.append(key);
            sb.append(":");
            sb.append(variable.get(key));
            sb.append("\n");
        }

        sb.append("}");
        return sb.toString();
    }
}
