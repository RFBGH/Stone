package com.flypig.stone.bnf;

import com.flypig.stone.ast.ClassBody;
import com.flypig.stone.ast.ClassStmnt;
import com.flypig.stone.ast.Dot;
import com.flypig.stone.lexer.token.Token;
import com.flypig.stone.parser.Parser;

import static com.flypig.stone.parser.Parser.rule;

public class ClassParser extends FuncParser{

    Parser member = rule().or(def, simple);
    Parser classBody = rule(ClassBody.class).sep(Token.EOL).sep("{").option(member).repeat(rule().sep(";", Token.EOL).option(member)).sep("}");
    Parser defClass = rule(ClassStmnt.class).sep("class").identifier(reserved).option(rule().sep("extends").identifier(reserved)).ast(classBody);

    public ClassParser(){
        postfix.insertChoice(rule(Dot.class).sep(".").identifier(reserved));
        program.insertChoice(defClass);
    }
}
