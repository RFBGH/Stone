package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.exception.ParseException;
import com.flypig.stone.lexer.Lexer;

import java.util.List;

public class OrTree implements Element{

    protected List<Parser> parsers;
    protected OrTree(List<Parser> p){
        parsers = p;
    }

    @Override
    public void parse(Lexer lexer, List<ASTree> res) throws ParseException {

        Parser parser = choose(lexer);
        if(parser == null){
            throw new ParseException(lexer.peek(0));
        }
        res.add(parser.parse(lexer));
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        Parser parser = choose(lexer);
        return parser != null;
    }

    protected Parser choose(Lexer lexer) throws ParseException{
        for(Parser parser: parsers){
            if(parser.match(lexer)){
                return parser;
            }
        }
        return null;
    }

    protected void insert(Parser p){
        parsers.add(0, p);
    }
}
