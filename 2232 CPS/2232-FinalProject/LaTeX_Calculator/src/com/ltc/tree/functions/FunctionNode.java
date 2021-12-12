package com.ltc.tree.functions;

import com.ltc.tree.NodeType;
import com.ltc.tree.ValueNode;

public abstract class FunctionNode extends ValueNode {
    @Override
    public NodeType getType() {
        return NodeType.BRANCH;
    }

    public FunctionNode(ValueNode left, ValueNode right) {
        super(0.0); // for Function Node, the default data is 0.0.
        this.left = left;
        this.right = right;
    }

    /**
     * Calculate the result of left node, if left node is a branch node, then it would perform the process() method of
     * that node, if not, it would return the data within that node.
     * @return the result of the left node
     */
    public double calculateLeftNode() {
        return left.isBranchNode() ? ((FunctionNode) left).process() : left.data;
    }

    /**
     * Calculate the result of right node, if right node is a branch node, then it would perform the process() method of
     * that node, if not, it would return the data within that node.
     * @return the result of the right node
     */
    public double calculateRightNode() {
        return right.isBranchNode() ? ((FunctionNode) right).process() : right.data;
    }

    /**
     * Process the data or node within current node.
     * @return the result of current node
     */
    public abstract double process();

    public void append(ValueNode node) {
        _append(this, node);
    }

    private void _append(FunctionNode root, ValueNode node) {
        if (root.left == null) {
            root.left = node;
        } else {
            _append((FunctionNode) root.left, node);
        }
    }
}
