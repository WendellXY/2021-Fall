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

    /**
     * Append node to the tree, it would always add new node to the left-most node, which left node is null
     * @param node the node to add to the tree
     */
    public void append(ValueNode node) {
        _append(this, node);
    }

    /**
     * The recursive implementation of the public append function
     * @param currentNode the current node to add new node
     * @param newNode the new node to be added to the current node
     */
    private void _append(FunctionNode currentNode, ValueNode newNode) {
        if (currentNode.left == null) {
            currentNode.left = newNode;
        } else {
            _append((FunctionNode) currentNode.left, newNode);
        }
    }
}
