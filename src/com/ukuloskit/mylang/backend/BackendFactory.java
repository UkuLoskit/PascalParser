package com.ukuloskit.mylang.backend;

import com.ukuloskit.mylang.backend.compiler.CodeGenerator;
import com.ukuloskit.mylang.frontend.EofToken;
import com.ukuloskit.mylang.backend.interpreter.Executor;

/**
 * Created by uku on 7.11.16.
 */
public class BackendFactory {

    public static Backend createBackend(String operation) throws Exception {
        if (operation.equalsIgnoreCase("compile")) {
            return new CodeGenerator();
        } else if (operation.equalsIgnoreCase("execute")) {
            return new Executor();
        } else {
            throw new Exception("Backend factory: Invalid operation '" + operation + "'");
        }

    }
}
