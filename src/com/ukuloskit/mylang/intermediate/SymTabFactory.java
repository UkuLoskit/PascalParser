package com.ukuloskit.mylang.intermediate;

import com.ukuloskit.mylang.intermediate.symtabimpl.SymTabEntryImpl;
import com.ukuloskit.mylang.intermediate.symtabimpl.SymTabImpl;
import com.ukuloskit.mylang.intermediate.symtabimpl.SymTabStackImpl;

/**
 * Created by uku on 20.11.16.
 */
public class SymTabFactory {
    public static SymTabStack createSymTabStack() {
        return new SymTabStackImpl();
    }

    public static SymTab createSymTab(int nestingLevel) {
        return new SymTabImpl(nestingLevel);
    }

    public static SymTabEntry createSymTabEntry(String name, SymTab symTab) {
        return new SymTabEntryImpl(name, symTab);
    }
}
