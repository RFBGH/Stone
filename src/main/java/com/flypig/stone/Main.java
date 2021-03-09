package com.flypig.stone;

import com.flypig.stone.lexer.Lexer;
import com.flypig.stone.lexer.token.Token;

import java.io.FileReader;

public class Main {

    public static void main(String[] args){
        System.out.println("hello stone");

        try{

            FileReader reader = new FileReader("/Users/admin/stone");
            Lexer lexer = new Lexer(reader);
            while (true){
                Token token = lexer.read();
                System.out.println(token.toString());
                if(token == Token.EOF){
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
