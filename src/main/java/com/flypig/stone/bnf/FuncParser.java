package com.flypig.stone.bnf;

import com.flypig.stone.ast.Args;
import com.flypig.stone.ast.DefStmnt;
import com.flypig.stone.ast.ParamList;
import com.flypig.stone.parser.Parser;

import static com.flypig.stone.parser.Parser.rule;

public class FuncParser extends BasicParser{

    Parser param = rule().identifier(reserved);
    Parser params = rule(ParamList.class).ast(param).repeat(rule().sep(",").ast(param));
    Parser paramList = rule().sep("(").maybe(params).sep(")");
    Parser def = rule(DefStmnt.class).sep("def").identifier(reserved).ast(paramList).ast(block);
    Parser args = rule(Args.class).ast(expr).repeat(rule().sep(",").ast(expr));
    Parser postfix = rule().sep("(").maybe(args).sep(")");

    public FuncParser(){
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        program.insertChoice(def);
    }
}
