package com.opensource;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Aaron on 13/12/2016.
 */
public class NashornTest {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );
        System.out.println( engine.getClass().getName() );
        System.out.println( "Result:" + engine.eval( "function f() { var sum=0; for(var i=0;i<100;i++){sum+=i;}return sum; }; f();" ) );
    }
}
