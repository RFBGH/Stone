package com.flypig.stone;

import com.flypig.stone.ast.ASTree;
import com.flypig.stone.ast.DefStmnt;
import com.flypig.stone.bnf.BasicParser;
import com.flypig.stone.bnf.ClassParser;
import com.flypig.stone.bnf.FuncParser;
import com.flypig.stone.execute.Context;
import com.flypig.stone.execute.ExecutorFactory;
import com.flypig.stone.lexer.Lexer;
import com.flypig.stone.lexer.token.Token;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

/**
 * s = "ab" + "cd"
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
 * sum = even + odd + fact(10)
 *
 * def fact (n)
 * {
 * 	f = 1
 * 	while n > 0
 *    {
 * 		f = f * n
 * 		n = n - 1
 *    }
 * 	f
 * }
 */
public class Main {

    public static void main(String[] args){
        System.out.println("hello stone");

        try{

            FileReader reader = new FileReader("E:/stone.txt");
            Lexer lexer = new Lexer(reader);

            ClassParser basicParser = new ClassParser();
//            Context context = new Context(null);

//            List<ASTree>list = new ArrayList<>();

            while (lexer.peek(0) != Token.EOF){
                ASTree ast = basicParser.parse(lexer);
//                if(ast instanceof DefStmnt){
//                    context.putFunc((DefStmnt) ast);
//                }else{
//                    list.add(ast);
//                }

                System.out.println(ast.toString());
            }

//            for(ASTree tree : list){
//                Object result = ExecutorFactory.getInstance().execute(tree, context);
//                System.out.println(result);
//            }

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
