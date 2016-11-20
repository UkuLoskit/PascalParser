package com.ukuloskit.mylang.intermediate;

/**
 * Created by uku on 19.11.16.
 */
public interface SymTabStack {

    public int getCurrentStackNestingLevel();

    public SymTab getLocalSymTab();

    public SymTabEntry enterLocal(String name);

    public SymTabEntry lookupLocal(String name);

    public SymTabEntry lookup(String name);
}
