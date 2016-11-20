package com.ukuloskit.mylang.intermediate.symtabimpl;

import com.ukuloskit.mylang.intermediate.SymTab;
import com.ukuloskit.mylang.intermediate.SymTabEntry;

import java.util.ArrayList;

public class SymTabImpl implements SymTab {
    public SymTabImpl(int nestingLevel) {
    }

    @Override
    public int getNestingLevel() {
        return 0;
    }

    @Override
    public SymTabEntry enter(String name) {
        return null;
    }

    @Override
    public SymTabEntry lookup(String name) {
        return null;
    }

    @Override
    public ArrayList<SymTabEntry> sortedEntries() {
        return null;
    }
}
