package com.flypig.stone.parser;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.exception.ParseException;
import com.flypig.stone.lexer.Lexer;

import java.util.List;

public interface Element {

    void parse(Lexer lexer, List<ASTree> res) throws ParseException;

    boolean match(Lexer lexer) throws ParseException;
}
