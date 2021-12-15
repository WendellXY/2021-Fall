package com.ltc.tree.functions;

import com.ltc.tree.ValueNode;

public class TriSinNode extends FunctionNode {
    public TriSinNode(ValueNode left, ValueNode right) {
        super(left, right);
    }

    @Override
    public double process() {
        return Math.sin(LeftOperand());
    }
}
