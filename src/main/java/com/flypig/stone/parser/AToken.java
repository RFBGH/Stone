package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTLeaf;
import com.flypig.stone.ast.ASTree;
import com.flypig.stone.exception.ParseException;
import com.flypig.stone.lexer.Lexer;
import com.flypig.stone.lexer.token.Token;

import java.util.List;

public abstract class AToken implements Element{

    protected Factory factory;
    protected AToken(Class<? extends ASTLeaf> clazz){
        if(clazz == null){
            clazz = ASTLeaf.class;
        }
        factory = Factory.get(clazz, Token.class);
    }

    @Override
    public void parse(Lexer lexer, List<ASTree> res) throws ParseException {
        Token t = lexer.read();
        if(test(t)){
            ASTree leaf = factory.make(t);
            res.add(leaf);
        }else{
            throw new ParseException(t);
        }
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        return test(lexer.peek(0));
    }

    protected abstract boolean test(Token t);
}
