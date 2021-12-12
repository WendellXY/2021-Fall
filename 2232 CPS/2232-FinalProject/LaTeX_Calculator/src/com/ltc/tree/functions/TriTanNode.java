package com.ltc.tree.functions;

import com.ltc.tree.ValueNode;

public class TriTanNode extends FunctionNode {
    public TriTanNode(ValueNode left, ValueNode right) {
        super(left, right);
    }

    @Override
    public double process() {
        return Math.tan(calculateLeftNode());
    }
}
