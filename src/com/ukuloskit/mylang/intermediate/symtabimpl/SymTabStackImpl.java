package com.ukuloskit.mylang.intermediate.symtabimpl;

import com.ukuloskit.mylang.intermediate.SymTab;
import com.ukuloskit.mylang.intermediate.SymTabEntry;
import com.ukuloskit.mylang.intermediate.SymTabFactory;
import com.ukuloskit.mylang.intermediate.SymTabStack;

import java.util.ArrayList;

public class SymTabStackImpl extends ArrayList<SymTab> implements SymTabStack {

    public SymTabStackImpl() {
        currentNestingLevel = 0;
        add(SymTabFactory.createSymTab((currentNestingLevel)));
    }

    private int currentNestingLevel;
    @Override
    public int getCurrentStackNestingLevel() {
        return currentNestingLevel;
    }

    @Override
    public SymTab getLocalSymTab() {
        return get(currentNestingLevel);
    }

    @Override
    public SymTabEntry enterLocal(String name) {
        return get(currentNestingLevel).enter(name);
    }

    @Override
    public SymTabEntry lookupLocal(String name) {
        return get(currentNestingLevel).lookup(name);
    }

    @Override
    public SymTabEntry lookup(String name) {
        return lookupLocal(name);
    }
}
