package org.petuch03;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavascriptExecutor {
    public static String executeJavascript(String input) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            Object result = engine.eval(input);
            return result.toString();
        } catch (ScriptException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }
}