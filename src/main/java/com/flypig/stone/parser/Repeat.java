package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTList;
import com.flypig.stone.ast.ASTree;
import com.flypig.stone.exception.ParseException;
import com.flypig.stone.lexer.Lexer;

import java.util.List;

public class Repeat implements Element {

    protected Parser parser;
    protected boolean once;
    protected Repeat(Parser p, boolean once){
        parser = p;
        this.once = once;
    }

    @Override
    public void parse(Lexer lexer, List<ASTree> res) throws ParseException {
        while (parser.match(lexer)){
            ASTree t = parser.parse(lexer);

            if(t.getClass() != ASTList.class || t.getChildCount() > 0){
                res.add(t);
            }

            if(once){
                break;
            }
        }
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        return parser.match(lexer);
    }
}
