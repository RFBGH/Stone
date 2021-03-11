package com.flypig.stone;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.bnf.BasicParser;
import com.flypig.stone.bnf.FuncParser;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.ExecutorFactory;
import com.flypig.stone.lexer.Lexer;
import com.flypig.stone.lexer.token.Token;

import java.io.FileReader;


/**
 * i = 0
 * even = 0
 * odd = 0 # this is commit
 * test = (2+4)*3
 * s = "this is string aaa \" asdfat "
 * while i < 10
 * {
 * 	if i % 2 == 0
 *        {
 * 		even = even+i
 *    }
 * 	else
 *    {
 * 		odd = odd + i
 *    }
 * 	i = i + 1
 * }
 * even + odd
 */

public class Main {

    public static void main(String[] args){
        System.out.println("hello stone");

        try{

            FileReader reader = new FileReader("/Users/admin/stone");
            Lexer lexer = new Lexer(reader);

            FuncParser basicParser = new FuncParser();
            Context context = new Context(null);
            while (lexer.peek(0) != Token.EOF){
                ASTree ast = basicParser.parse(lexer);
//                Object result = ExecutorFactory.getInstance().execute(ast, context);
                System.out.println(ast.toString());
//                System.out.println(result);
            }

//            System.out.println(context.toString());

//            while (true){
//                Token token = lexer.read();
//                System.out.println(token.toString());
//                if(token == Token.EOF){
//                    break;
//                }
//            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
