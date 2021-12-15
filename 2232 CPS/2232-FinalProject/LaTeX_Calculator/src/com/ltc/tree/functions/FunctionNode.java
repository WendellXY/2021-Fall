package com.ltc.tree.functions;

import com.ltc.tree.NodeType;
import com.ltc.tree.ValueNode;

/**
 * The Function Node is the abstract class of all LaTeX operator that were implementation, and you can easily extend the
 * supported LaTeX operator by inherit this class.
 *
 * For example, if you want the calculator to support factorial operations, which are not yet supported, you can simply
 * inherit from the com.ltc.tree.functions package FunctionNode is an abstract class with a mathematical formula, and
 * then you can add the corresponding mathematical notation to FunctionNodeMapper to make the program support this
 * calculation. The implementation of addition and sine operator in this LaTeX calculator is given below as example to
 * show how simple it would be extended.
 *
 *      public class AdditionNode extends FunctionNode {
 *          public AdditionNode(ValueNode left, ValueNode right) { super(left, right); }
 *
 *          @Override
 *          public double process() { return LeftOperand() + RightOperand(); }
 *      }
 *
 *      public class TriSinNode extends FunctionNode {
 *          public TriSinNode(ValueNode left, ValueNode right) { super(left, right); }
 *
 *          @Override
 *          public double process() { return Math.sin(LeftOperand()); }
 *      }
 */
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
    public double LeftOperand() {
        return left.isBranchNode() ? ((FunctionNode) left).process() : left.data;
    }

    /**
     * Calculate the result of right node, if right node is a branch node, then it would perform the process() method of
     * that node, if not, it would return the data within that node.
     * @return the result of the right node
     */
    public double RightOperand() {
        return right.isBranchNode() ? ((FunctionNode) right).process() : right.data;
    }

    /**
     * Process the data or node within current node.
     *
     * If you are creating your own FunctionNode, here is some suggestion:
     *
     * 0. inherit the abstract class such that you could create your custom operator
     * 1. if the new node (operator) is a unary operator, the left operand is the only operand that you should care
     * 2. You could get the value of the left operand by the method LeftOperand(), and the right operand by
     *      RightOperand()
     * 3. The initializer should always be like below, DO NOT CHANGE IT unless you are really confident and really
     *      familiar with the whole project.
     *
     *      ```java
     *          public YOUR_NODE_NAME(ValueNode left, ValueNode right) {
     *              super(left, right);
     *          }
     *      ```
     * 4. Usually, the process() method is the only method that you need to take care for this abstract class
     *
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
