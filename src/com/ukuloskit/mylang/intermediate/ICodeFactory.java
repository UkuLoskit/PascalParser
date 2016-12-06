package com.ukuloskit.mylang.intermediate;

import com.ukuloskit.mylang.intermediate.icodeimpl.ICodeImpl;

public class ICodeFactory {
    public static ICode createICode() {
        return new ICodeImpl();
    }

    public static ICodeNode createICodeNode(ICodeNodeType type) {
        return new ICodeNodeImpl(type);
    }
}
