package com.ltc.tree.functions;

import com.ltc.tree.ValueNode;

public class MultiplicationNode extends FunctionNode {
    public MultiplicationNode(ValueNode left, ValueNode right) {
        super(left, right);
    }

    @Override
    public double process() {
        return LeftOperand() * RightOperand();
    }
}
