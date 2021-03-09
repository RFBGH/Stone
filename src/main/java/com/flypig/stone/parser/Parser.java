package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTLeaf;
import com.flypig.stone.ast.ASTree;
import com.flypig.stone.exception.ParseException;
import com.flypig.stone.lexer.Lexer;

import java.util.*;

public class Parser {

    protected List<Element> elements;
    protected Factory factory;

    public Parser(Class<? extends ASTree> clazz){
        reset(clazz);
    }

    protected Parser(Parser p){
        elements = p.elements;
        factory = p.factory;
    }

    public ASTree parse(Lexer lexer) throws ParseException{
        List<ASTree> results = new ArrayList<>();
        for(Element e : elements){
            e.parse(lexer, results);
        }
        return factory.make(results);
    }

    protected boolean match(Lexer lexer) throws ParseException{
        if(elements.size() == 0){
            return true;
        }

        Element e = elements.get(0);
        return e.match(lexer);
    }

    public Parser reset(){
        elements = new ArrayList<>();
        return this;
    }

    public Parser reset(Class<? extends ASTree> clazz){
        elements = new ArrayList<>();
        factory = Factory.getForASTList(clazz);
        return this;
    }

    public Parser number(){
        return number(null);
    }

    public Parser number(Class<? extends ASTLeaf> clazz){
        elements.add(new NumberToken(clazz));
        return this;
    }

    public Parser identifier(Set<String> reserved){
        return identifier(null, reserved);
    }

    public Parser identifier(Class<?extends ASTLeaf> clazz, Set<String> reserved){
        elements.add(new IdToken(clazz, reserved));
        return this;
    }

    public Parser string(){
        return string(null);
    }

    public Parser string(Class<? extends  ASTLeaf> clazz){
        elements.add(new StrToken(clazz));
        return this;
    }

    public Parser token(String... pat){
        elements.add(new Leaf(pat));
        return this;
    }

    public Parser sep(String... pat){
        elements.add(new Skip(pat));
        return this;
    }

    public Parser ast(Parser p){
        elements.add(new Tree(p));
        return this;
    }

    public Parser or(Parser...ps){
        List<Parser> parsers = new ArrayList<>(Arrays.asList(ps));
        elements.add(new OrTree(parsers));
        return this;
    }

    public Parser maybe(Parser p){
        Parser p2 = new Parser(p);
        p2.reset();
        List<Parser> parsers = new ArrayList<>();
        parsers.add(p);
        parsers.add(p2);
        elements.add(new OrTree(parsers));
        return this;
    }

    public Parser option(Parser p){
        elements.add(new Repeat(p, true));
        return this;
    }

    public Parser repeat(Parser p){
        elements.add(new Repeat(p, false));
        return this;
    }

    public Parser expression(Parser subexp, Operators operators){
        return expression(null, subexp, operators);
    }

    public Parser expression(Class<? extends ASTree> clazz, Parser subexp, Operators operators){
        elements.add(new Expr(clazz, subexp, operators));
        return this;
    }

    public Parser insertChoice(Parser p){
        Element e = elements.get(0);
        if(e instanceof OrTree){
            ((OrTree) e).insert(p);
        }else{
            Parser otherwise = new Parser(this);
            reset(null);
            or(p, otherwise);
        }
        return this;
    }

    public static Parser rule(){
        return rule(null);
    }

    public static Parser rule(Class<? extends ASTree> clazz){
        return new Parser(clazz);
    }
}
