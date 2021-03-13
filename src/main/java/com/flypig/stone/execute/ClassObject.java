package com.flypig.stone.execute;

public class ClassObject {

    private Context context;
    private String name;

    public ClassObject(String name, Context parent) {
        this.context = new Context(parent);
        this.name = name;
    }

    public Context getContext() {
        return context;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "class context "+name+" "+context.toString();
    }
}
