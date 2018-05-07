package com.github.basp1.extralib;

import java.util.*;

public class Tree {
    private TreeNode root;
    private Set<TreeNode> nodes;

    public Tree() {
        this.nodes = new HashSet<>();
        this.root = addNode(".");
    }

    public Set<TreeNode> getNodes() {
        return nodes;
    }

    public TreeNode addNode(Object value) {
        TreeNode node = new TreeNode(null, value);
        nodes.add(node);
        return node;
    }

    public void putEdge(TreeNode nodeU, TreeNode nodeV) {
        nodeV.setParent(nodeU);
        nodeU.addSuccessor(nodeV);
    }

    public TreeNode getRoot() {
        return root;
    }

    public List<TreeNode>[] getAllPaths() {
        List<List<TreeNode>> result = new ArrayList<>();

        Stack<TreeNode> path = new Stack<>();
        Stack<TreeNode> queue = new Stack<>();
        queue.push(getRoot());
        boolean backward = false;

        while (!queue.empty()) {
            TreeNode node = queue.pop();
            if (backward) {
                while (0 != path.size() && node.getParent() != path.pop().getParent()) ;
                backward = false;
            }
            path.push(node);

            if (0 == node.getSuccessors().size()) {
                List<TreeNode> t = new ArrayList<>();
                t.addAll(path);
                result.add(t);
                backward = true;
            }

            queue.addAll(node.getSuccessors());
        }

        return result.toArray(new List[0]);
    }
}