package com.ltc.tree.functions;

import com.ltc.tree.ValueNode;

public class AdditionNode extends FunctionNode {
    public AdditionNode(ValueNode left, ValueNode right) { super(left, right); }

    @Override
    public double process() {
        return LeftOperand() + RightOperand();
    }
}
