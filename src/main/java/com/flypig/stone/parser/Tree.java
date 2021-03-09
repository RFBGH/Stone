package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.exception.ParseException;
import com.flypig.stone.lexer.Lexer;

import java.util.List;

public class Tree implements Element{

    protected Parser parser;

    protected Tree(Parser p){
        this.parser = p;
    }

    @Override
    public void parse(Lexer lexer, List<ASTree> res) throws ParseException {
        res.add(parser.parse(lexer));
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        return parser.match(lexer);
    }
}
