package com.ukuloskit.mylang.intermediate.icodeimpl;

import com.ukuloskit.mylang.intermediate.ICode;
import com.ukuloskit.mylang.intermediate.ICodeNode;

public class ICodeImpl implements ICode {
    private ICodeNode root;
    @Override
    public ICodeNode setRoot(ICodeNode node) {
        root = node;
        return root;
    }

    @Override
    public ICodeNode getRoot() {
        return root;
    }
}
