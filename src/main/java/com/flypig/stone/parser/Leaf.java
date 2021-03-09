package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTLeaf;
import com.flypig.stone.ast.ASTree;
import com.flypig.stone.exception.ParseException;
import com.flypig.stone.lexer.Lexer;
import com.flypig.stone.lexer.token.Token;

import java.util.List;

public class Leaf implements Element{

    protected String[] tokens;
    protected Leaf(String[] pat){
        tokens = pat;
    }

    @Override
    public void parse(Lexer lexer, List<ASTree> res) throws ParseException {

        Token t = lexer.read();
        if(t.isIdentifier()){
            for(String token:tokens){
                if(token.equals(t.getText())){
                    find(res, t);
                    return;
                }
            }
        }
        if(tokens.length > 0){
            throw new ParseException(tokens[0]+" expected.", t);
        }
    }

    protected void find(List<ASTree>res, Token t){
        res.add(new ASTLeaf(t));
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {

        Token t = lexer.peek(0);
        if(t.isIdentifier()){
            for(String token : tokens){
                if(token.equals(t.getText())){
                    return true;
                }
            }
        }
        return false;
    }
}
