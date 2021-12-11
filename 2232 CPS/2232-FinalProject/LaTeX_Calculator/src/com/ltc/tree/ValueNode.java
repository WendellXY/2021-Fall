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

    public ValueNode toNegative() {
        ValueNode node = new ValueNode(-this.data);
        node.left = this.left;
        node.right = this.right;
        return node;
    }

    public ValueNode toReciprocal() {
        ValueNode node = new ValueNode(1/this.data);
        node.left = this.left;
        node.right = this.right;
        return node;
    }
}
