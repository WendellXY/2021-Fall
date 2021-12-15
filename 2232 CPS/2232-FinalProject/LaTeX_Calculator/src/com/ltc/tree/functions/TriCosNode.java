package com.ltc.tree.functions;

import com.ltc.tree.ValueNode;

public class TriCosNode extends FunctionNode {
    public TriCosNode(ValueNode left, ValueNode right) { super(left, right); }

    @Override
    public double process() {
        return Math.cos(LeftOperand());
    }
}
