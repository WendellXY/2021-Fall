package com.ltc.tree.functions;

import com.ltc.tree.ValueNode;

public class TriSinNode extends FunctionNode {
    public TriSinNode(ValueNode left, ValueNode right) {
        super(left, right);
    }

    @Override
    double process() {
        return Math.sin(calculateLeftNode());
    }
}
