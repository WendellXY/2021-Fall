package com.ltc.tree;

public class ValueNode {
    public final double data;

    public ValueNode left;
    public ValueNode right;

    /**
     * @return the type of current node
     */
    public NodeType getType() {
        return NodeType.LEAF;
    }

    public ValueNode(double data) {
        this.data = data;
    }

    public boolean isBranchNode() {
        return getType() == NodeType.BRANCH;
    }

    public boolean isLeafNode() {
        return getType() == NodeType.LEAF;
    }

    /**
     * @return the negative ValueNode, that is a node which data is the negative form of current node's data
     */
    public ValueNode toNegative() {
        ValueNode node = new ValueNode(-this.data);
        node.left = this.left;
        node.right = this.right;
        return node;
    }

    /**
     * @return the reciprocal ValueNode, that is a node which data is the reciprocal of current node's data
     */
    public ValueNode toReciprocal() {
        ValueNode node = new ValueNode(1/this.data);
        node.left = this.left;
        node.right = this.right;
        return node;
    }

    @Override
    public String toString() {
        if (isLeafNode()) {
            return "Number(" + data + ')';
        } else {
            return "Function(" +
                   "left: " + (left == null ? "null" : left.toString()) +
                   ", right: " + (right == null ? "null" : right.toString()) +
                   ")";
        }
    }
}
