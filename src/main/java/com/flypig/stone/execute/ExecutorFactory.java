package com.flypig.stone.execute;

import com.flypig.stone.ast.*;
import com.flypig.stone.execute.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ExecutorFactory {

    private static final ExecutorFactory sInstance = new ExecutorFactory();
    public static ExecutorFactory getInstance(){
        return sInstance;
    }

    private Map<Class<? extends ASTree>, IExecutor> map = new HashMap<>();
    private ExecutorFactory(){
        map.put(WhileStmnt.class, new WhileStmntExecutor());
        map.put(IfStmnt.class, new IfStmntExecutor());
        map.put(NegativeExpr.class, new NegativeExprExecutor());
        map.put(NullStmnt.class, new NullStmntExecutor());
        map.put(Name.class, new NameExecutor());
        map.put(BlockStmnt.class, new BlockStmntExecutor());
        map.put(NumberLiteral.class, new NumberLiteralExecutor());
        map.put(PrimaryExpr.class, new PrimaryExprExecutor());
        map.put(BinaryExpr.class, new BinaryExprExecutor());
        map.put(StringLiteral.class, new StringLiteralExecutor());
    }

    public Object execute(ASTree asTree, Context context){
        IExecutor executor = map.get(asTree.getClass());
        if(executor == null){
            throw new RuntimeException("not found executor " + asTree.toString());
        }
        return executor.execute(asTree, context);
    }
}
