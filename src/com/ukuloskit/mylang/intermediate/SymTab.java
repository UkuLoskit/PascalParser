package com.ukuloskit.mylang.intermediate;

import java.util.ArrayList;

/**
 * Created by uku on 7.11.16.
 */
public interface SymTab {

    public int getNestingLevel();

    public SymTabEntry enter(String name);
    public SymTabEntry lookup(String name);
    public ArrayList<SymTabEntry> sortedEntries();

}
