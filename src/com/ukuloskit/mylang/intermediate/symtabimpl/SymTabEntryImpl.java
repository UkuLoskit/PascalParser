package com.ukuloskit.mylang.intermediate.symtabimpl;

import com.ukuloskit.mylang.intermediate.SymTab;
import com.ukuloskit.mylang.intermediate.SymTabEntry;
import com.ukuloskit.mylang.intermediate.SymTabKey;

import java.util.ArrayList;

public class SymTabEntryImpl extends ArrayList<SymTab> implements  {

    public SymTabEntryImpl(String name, SymTab symTab) {
        this.cu
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public SymTab getSymTab() {
        return null;
    }

    @Override
    public void appendLineNumber(int lineNumber) {

    }

    @Override
    public ArrayList<SymTab> getLineNumbers() {
        return null;
    }

    @Override
    public void setAttribute(SymTabKey key, Object value) {

    }

    @Override
    public Object getAttribute(SymTabKey key) {
        return null;
    }
}
