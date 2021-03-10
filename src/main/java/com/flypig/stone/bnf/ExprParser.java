package com.flypig.stone.bnf;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.BinaryExpr;
import com.flypig.stone.ast.Name;
import com.flypig.stone.ast.NumberLiteral;
import com.flypig.stone.exception.ParseException;
import com.flypig.stone.lexer.Lexer;
import com.flypig.stone.lexer.token.Token;

import java.util.Arrays;

public class ExprParser {

    private Lexer lexer;
    public ExprParser(Lexer lexer){
        this.lexer = lexer;
    }

    private boolean isToken(String name) throws ParseException{

        Token token = lexer.peek(0);
        if(token.isIdentifier() && token.getText().equals(name)){
            return true;
        }
        return false;
    }

    private void token(String name) throws ParseException{

        Token token = lexer.read();
        if(token.isIdentifier() && token.getText().equals(name)){
            return;
        }
        throw new ParseException(name+" is need but toke is "+token.toString());
    }

    public ASTree factor(Lexer lexer) throws ParseException{
        if(isToken("(")){
            token("(");
            ASTree ast = expression(lexer);
            token(")");
            return ast;
        }else{
            return new NumberLiteral(lexer.read());
        }
    }

    public ASTree term(Lexer lexer) throws ParseException{

        ASTree left = factor(lexer);
        if(isToken("*") || isToken("/")){
            ASTree op = new Name(lexer.read());
            ASTree right = factor(lexer);
            left = new BinaryExpr(Arrays.asList(left, op, right));
        }

        return left;
    }

    public ASTree expression(Lexer lexer) throws ParseException{

        ASTree left = term(lexer);
        if(isToken("+") || isToken("-")){
            ASTree op = new Name(lexer.read());
            ASTree right = term(lexer);
            left = new BinaryExpr(Arrays.asList(left, op, right));
        }
        return left;
    }
}
