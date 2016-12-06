package com.ukuloskit.mylang.intermediate;

import java.util.ArrayList;

public class ICodeNodeImpl implements ICodeNode {
    public ICodeNodeImpl(ICodeNodeType type) {
    }

    @Override
    public ICodeNodeType getType() {
        return null;
    }

    @Override
    public ICodeNode getParent() {
        return null;
    }

    @Override
    public ICodeNode addChild(ICodeNode node) {
        return null;
    }

    @Override
    public ArrayList<ICodeNode> getChildren() {
        return null;
    }

    @Override
    public void setAttribute(ICodeKey key, Object value) {

    }

    @Override
    public Object getAttribute(ICodeKey key) {
        return null;
    }

    @Override
    public ICodeNode copy() {
        return null;
    }
}
