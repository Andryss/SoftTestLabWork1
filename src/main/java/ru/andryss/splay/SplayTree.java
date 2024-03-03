package ru.andryss.splay;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class SplayTree {

    private Node root;

    private Node doSearch(Node node, int key) {
        if (node == null || key == node.key) {
            return node;
        }

        if (key < node.key) {
            return doSearch(node.left, key);
        }
        return doSearch(node.right, key);
    }

    // rotate left at node x
    //    x                r
    //   / \              / \
    //  1   r     ->     x   3
    //     / \          / \
    //    2   3        1   2
    private void leftRotate(Node x) {
        Node right = x.right;
        x.right = right.left;
        if (right.left != null) {
            right.left.parent = x;
        }
        right.parent = x.parent;
        if (x.parent == null) {
            root = right;
        } else if (x == x.parent.left) {
            x.parent.left = right;
        } else {
            x.parent.right = right;
        }
        right.left = x;
        x.parent = right;
    }

    // rotate right at node x
    //      x            l
    //     / \          / \
    //    l   3   ->   1   x
    //   / \              / \
    //  1   2            2   e
    private void rightRotate(Node x) {
        Node left = x.left;
        x.left = left.right;
        if (left.right != null) {
            left.right.parent = x;
        }
        left.parent = x.parent;
        if (x.parent == null) {
            this.root = left;
        } else if (x == x.parent.right) {
            x.parent.right = left;
        } else {
            x.parent.left = left;
        }
        left.right = x;
        x.parent = left;
    }

    // Splaying operation. It moves node to the root of the tree
    private void splay(Node node) {
        Node parent = node.parent;
        while (parent != null) {
            if (parent.parent == null) {
                if (node == parent.left) {
                    // zig rotation
                    rightRotate(parent);
                } else {
                    // zag rotation
                    leftRotate(parent);
                }
            } else if (node == parent.left && parent == parent.parent.left) {
                // zig-zig rotation
                rightRotate(parent.parent);
                rightRotate(parent);
            } else if (node == parent.right && parent == parent.parent.right) {
                // zag-zag rotation
                leftRotate(parent.parent);
                leftRotate(parent);
            } else if (node == parent.right && parent == parent.parent.left) {
                // zig-zag rotation
                leftRotate(parent);
                parent = node.parent;
                rightRotate(parent);
            } else {
                // zag-zig rotation
                rightRotate(parent);
                parent = node.parent;
                leftRotate(parent);
            }
            parent = node.parent;
        }
    }

    // joins two trees left and right
    private Node join(Node left, Node right){
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        Node leftMax = maximum(left);
        splay(leftMax);
        leftMax.right = right;
        right.parent = leftMax;
        return leftMax;
    }

    // find the node with the maximum key
    private Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // search the tree for the key
    // and return the corresponding node
    // if node is not present - null
    public Node search(int key) {
        Node node = doSearch(root, key);
        if (node != null) {
            splay(node);
        }
        return node;
    }

    // insert the key to the tree in its appropriate position
    // if key is already present - ignore
    public Node insert(int key) {
        Node newNode = new Node(key);
        Node parent = null;
        Node node = root;

        // find empty leaf
        while (node != null) {
            parent = node;
            if (key == node.key) {
                return null;
            } else if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        newNode.parent = parent;
        if (parent == null) {
            root = newNode;
        } else if (newNode.key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        // splay newNode
        splay(newNode);
        return newNode;
    }

    // delete the node from the tree and return it
    // if node does not present - return null
    public Node delete(int key) {
        Node node = root;
        while (node != null){
            if (node.key == key) {
                break;
            }

            if (node.key < key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (node == null) {
            return null;
        }
        // split operation
        splay(node);

        Node right = node.right;
        if (right != null) {
            right.parent = null;
            node.right = null;
        }
        Node left = node.left;
        if (left != null){
            left.parent = null;
            node.left = null;
        }

        // join operation
        root = join(left, right);
        return node;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Node {
        private final int key;
        private Node parent, left, right;
    }
}
