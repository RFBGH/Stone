package com.flypig.stone.ast;

import java.util.ArrayList;
import java.util.List;

public class ClassBody extends ASTList{
    public ClassBody(List<ASTree> children) {
        super(children);
    }

    ASTree getVariables(){
        List<ASTree> names = new ArrayList<>();
        for(ASTree chid:this){
            if(chid instanceof Name){
                names.add(chid);
            }
        }
        return new ASTList(names);
    }

    ASTree getDefs(){
        List<ASTree> defs = new ArrayList<>();
        for(ASTree chid:this){
            if(chid instanceof DefStmnt){
                defs.add(chid);
            }
        }
        return new ASTList(defs);
    }

    @Override
    public String toString() {
        return "(ClassBody "+getVariables()+"."+getDefs()+")";
    }
}
