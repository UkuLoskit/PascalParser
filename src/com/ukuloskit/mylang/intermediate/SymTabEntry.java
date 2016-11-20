package com.ukuloskit.mylang.intermediate;

import java.util.ArrayList;

/**
 * Created by uku on 19.11.16.
 */
public interface SymTabEntry {

    public String getName();
    public SymTab getSymTab();
    public void appendLineNumber(int lineNumber);
    public ArrayList<SymTab> getLineNumbers();
    void setAttribute(SymTabKey key, Object value);
    public Object getAttribute(SymTabKey key);
}
