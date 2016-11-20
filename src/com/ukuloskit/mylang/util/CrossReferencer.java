package com.ukuloskit.mylang.util;

import com.ukuloskit.mylang.intermediate.SymTab;
import com.ukuloskit.mylang.intermediate.SymTabEntry;
import com.ukuloskit.mylang.intermediate.SymTabStack;

import java.util.ArrayList;

public class CrossReferencer {
    private static final int NAME_WIDTH = 16;
    private static final String NAME_FROMAT = "%-" + NAME_WIDTH + "s";
    private static final String NUMBERS_LABEL = " Line numbers  ";
    private static final String NUMBERS_UNDERLINE = " --------- ";
    private static final String NUMBER_FORMAT = " %03d";

    public void print(SymTabStack symTabStack) {
        System.out.println("\n===== CROSS-REFERENCE TABLE =====");
        printColumnHeadings();

        printSymTab(symTabStack.getLocalSymTab());
    }

    private void printSymTab(SymTab localSymTab) {
        ArrayList<SymTabEntry> sorted = localSymTab.sortedEntries();
        for (SymTabEntry entry: sorted) {
            ArrayList<Integer> lineNumbers = entry.getLineNumbers();

            if (lineNumbers != null) {
                for (Integer lineNumber: lineNumbers) {
                    System.out.print(String.format(NUMBER_FORMAT, lineNumber));
                }
            }
            System.out.println();
        }
    }

    private void printColumnHeadings() {
        System.out.println();
        System.out.println();
    }


}
