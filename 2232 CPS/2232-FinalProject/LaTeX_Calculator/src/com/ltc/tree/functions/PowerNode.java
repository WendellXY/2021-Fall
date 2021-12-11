package com.ltc.tree.functions;

import com.ltc.tree.ValueNode;

public class PowerNode extends FunctionNode {
    public PowerNode(ValueNode left, ValueNode right) { super(left, right); }

    @Override
    public double process() {
        return Math.pow(calculateLeftNode(), calculateRightNode());
    }
}
