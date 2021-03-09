package com.flypig.stone.lexer;

import com.flypig.stone.exception.ParseException;
import com.flypig.stone.lexer.token.IdToken;
import com.flypig.stone.lexer.token.NumberToken;
import com.flypig.stone.lexer.token.StringToken;
import com.flypig.stone.lexer.token.Token;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.LinkedList;

public class Lexer {

    private static final int STATE_UNKNOWN = 0;
    private static final int STATE_ANNOTATION = 4;
    private static final int STATE_NUMBER = 1;
    private static final int STATE_STRING = 2;
    private static final int STATE_ID = 3;

    private LinkedList<Token> tokens = new LinkedList<>();
    private boolean hasMore = true;
    private LineNumberReader reader;

    public Lexer(Reader r){
        reader = new LineNumberReader(r);
    }

    public Token read() throws ParseException{
        fillQueue(0);

        if(tokens.isEmpty()){
            return Token.EOF;
        }
        return tokens.poll();
    }

    public Token peek(int index) throws ParseException{
        fillQueue(index);
        if(tokens.isEmpty()){
            return Token.EOF;
        }
        return tokens.get(index);
    }

    private void fillQueue(int index) throws ParseException {

        while (index >= tokens.size()){
            readLine();

            if(!hasMore){
                break;
            }
        }
    }

    private boolean isSplitChar(char c){
        if(c == ' '
                || c == '\t'
                || c == '\n'
                || c == '\r'
                || c == ';'){
            return true;
        }
        return false;
    }

    private boolean isOpChar(char c){
        if(c == '+'
                || c == '-'
                || c == '*'
                || c == '/'
                || c == '%'){
            return true;
        }
        return false;
    }

    private boolean isDoubleOpFirst(char c){
        if(c == '|'
                || c == '&'
                || c == '='
                || c == '!'
                || c == '>'
                || c == '<'){
            return true;
        }
        return false;
    }

    private boolean isLeftBracket(char c){
        if(c == '('
                || c == '['
                || c == '{'){
            return true;
        }

        return false;
    }

    private boolean isRightBracket(char c){
        if(c == ')'
                || c == ']'
                || c == '}'){
            return true;
        }

        return false;
    }

    private boolean isNumberChar(char c){
        if(c >= '0' && c <= '9'){
            return true;
        }
        return false;
    }

    private boolean isAZaz_Char(char c){
        if(c >= 'a' && c <= 'z'){
            return true;
        }

        if(c >= 'A' && c <= 'Z'){
            return true;
        }

        return c == '_';
    }

    private boolean isBracket(char c){
        return isLeftBracket(c) || isRightBracket(c);
    }

    private int dealUnknown(char c, int lineNo, StringBuilder sb) throws ParseException{

        if(isSplitChar(c)){
            return STATE_UNKNOWN;
        }

        if(isBracket(c)){
            tokens.add(new IdToken(lineNo, c+""));
            return STATE_UNKNOWN;
        }

        if(isOpChar(c)){
            tokens.add(new IdToken(lineNo, c+""));
            return STATE_UNKNOWN;
        }

        if(isDoubleOpFirst(c)){
            sb.append(c);
            return STATE_ID;
        }

        if(isNumberChar(c)){
            sb.append(c);
            return STATE_NUMBER;
        }

        if(isAZaz_Char(c)){
            sb.append(c);
            return STATE_ID;
        }

        if(c == '"'){
            return STATE_STRING;
        }

        if(c == '#'){
            return STATE_ANNOTATION;
        }

        throw new ParseException("no valid char "+c);
    }

    private int dealNumber(char c, int lineNo, StringBuilder sb) throws ParseException{

        if(c >= '0' && c <= '9'){
            sb.append(c);
            return STATE_NUMBER;
        }

        tokens.add(new NumberToken(lineNo, Integer.parseInt(sb.toString())));
        sb.delete(0, sb.length());
        return dealUnknown(c, lineNo, sb);
    }

    private int dealString(char c, int lineNo, StringBuilder sb) throws ParseException{

        if(c != '"'){
            sb.append(c);
            return STATE_STRING;
        }

        if(sb.length() > 0 && sb.charAt(sb.length()-1) == '\\'){
            sb.deleteCharAt(sb.length()-1);
            sb.append(c);
            return STATE_STRING;
        }

        tokens.add(new StringToken(lineNo, sb.toString()));
        sb.delete(0, sb.length());
        return STATE_UNKNOWN;
    }

    private int dealId(char c, int lineNo, StringBuilder sb) throws ParseException{

        char lastC = ' ';
        if(sb.length() > 0){
            lastC = sb.charAt(sb.length()-1);
        }

        if(!isDoubleOpFirst(lastC)){

            if(isAZaz_Char(c)){
                sb.append(c);
                return STATE_ID;
            }

            tokens.add(new IdToken(lineNo, sb.toString()));
            sb.delete(0, sb.length());
            return dealUnknown(c, lineNo, sb);
        }

        if(isAZaz_Char(c)){
            tokens.add(new IdToken(lineNo, sb.toString()));
            sb.delete(0, sb.length());
            sb.append(c);
            return STATE_ID;
        }

        if(((lastC == '!' || lastC == '>' || lastC == '<') && c == '=')
            ||  lastC == c){
            sb.append(c);
            tokens.add(new IdToken(lineNo, sb.toString()));
            sb.delete(0, sb.length());
            return STATE_UNKNOWN;
        }

        tokens.add(new IdToken(lineNo, sb.toString()));
        sb.delete(0, sb.length());
        return dealUnknown(c, lineNo, sb);
    }

    private void readLine() throws ParseException{

        String value = null;

        try {
            value = reader.readLine();
        }catch (IOException e){
            throw new ParseException(e.getMessage(), e);
        }

        if(value == null){
            hasMore = false;
            return;
        }

        int state = STATE_UNKNOWN;
        int lineNo = reader.getLineNumber();
        StringBuilder sb = new StringBuilder();

        for(int cur = 0; cur <= value.length(); cur++){

            try {
                char c = '\n';
                if(cur < value.length()){
                    c = value.charAt(cur);
                }
                switch (state){
                    case STATE_NUMBER:
                        state = dealNumber(c, lineNo, sb);
                        break;

                    case STATE_STRING:
                        state = dealString(c, lineNo, sb);
                        break;

                    case STATE_ID:
                        state = dealId(c, lineNo, sb);
                        break;

                    case STATE_UNKNOWN:
                        state = dealUnknown(c, lineNo, sb);
                        break;

                    case STATE_ANNOTATION:
                        cur = value.length();
                        break;
                }
            }catch (ParseException e){
                throw new ParseException(e.getMessage()+" line "+lineNo+" at "+cur, e);
            }

//            tokens.add(new IdToken(lineNo, Token.EOL));
        }
    }
}
