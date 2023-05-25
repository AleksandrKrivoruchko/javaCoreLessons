package ru.avk;

import java.util.Iterator;


public class BinaryTree {
    private Node root;
    private boolean res;

    public BinaryTree(Iterator<Integer> array) {
        while (array.hasNext()) {
            add(array.next());
        }
    }

    public void print() {
        print(root, "", null, true);
    }

    private static void print(Node node, String indent,
                              Boolean isRight, boolean lastNode) {
        System.out.print(indent);
        if (lastNode) {
            System.out.print("+-");
            indent += "  ";
        } else {
            System.out.print("|-");
            indent += "| ";
        }
        System.out.printf("[%s] %d%n",
                isRight == null ? "Root" : (isRight ? "R" : "L"),
                node.getValue());

        int childrenCount = 0;
        if (node.getRight() != null) {
            childrenCount++;
        }
        if (node.getLeft() != null) {
            childrenCount++;
        }
        if (node.getRight() != null) {
            print(node.getRight(), indent, true,
                    node.getLeft() == null);
        }
        if (node.getLeft() != null) {
            print(node.getLeft(), indent, false, true);
        }
    }

    public boolean contains(int value) {
        Node node = root;
        while (node != null) {
            if (node.getValue() == value) {
                return true;
            }
            if (node.getValue() > value) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return false;
    }

    public boolean containsV2(int value) {
        if (root == null) {
            return false;
        }
        return contains(root, value);
    }

    private boolean contains(Node node, int value) {
        return node.getValue() == value
                || contains(node.getValue() > value ? node.getLeft() : node.getRight(),
                value);
    }

    public boolean add(int value) {
        if (root == null) {
            root = new Node();
            root.setColor(Color.Black);
            root.setValue(value);
            return true;
        }

        boolean result = addNode(root, value);
        root = rebalance(root);
        root.setColor(Color.Black);
        return result;
    }

    private boolean addNode(Node node, int value) {
        if (node.getValue() == value) {
            return false;
        }
        if (node.getValue() > value) {
            if (node.getLeft() != null) {
                boolean result = addNode(node.getLeft(), value);
                node.setLeft(rebalance(node.getLeft()));
                return result;
            } else {
                node.setLeft(new Node());
                node.getLeft().setColor(Color.Red);
                node.getLeft().setValue(value);
                return true;
            }
        } else {
            if (node.getRight() != null) {
                boolean result = addNode(node.getRight(), value);
                node.setRight(rebalance(node.getRight()));
                return result;
            } else {
                node.setRight(new Node());
                node.getRight().setColor(Color.Red);
                node.getRight().setValue(value);
                return true;
            }
        }
    }

    public boolean remove(int value) {
        if (root == null) {
            return false;
        }
        removeNode(root, value);
        return res;
    }

    private Node removeNode(Node node, int value) {
        res = true;
        if (node.getValue() == value) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            Node minNodeInRightSubtree = findMinElement(node.getRight());
            node.setValue(minNodeInRightSubtree.getValue());
            node.setRight(removeNode(node.getRight(),
                    minNodeInRightSubtree.getValue()));
            return node;
        }

        if (node.getValue() > value) {
            if (node.getLeft() == null) {
                res = false;
                return node;
            }
            node.setLeft(removeNode(node.getLeft(), value));
            return node;
        } else {
            if (node.getRight() == null) {
                res = false;
                return node;
            }
            node.setRight(removeNode(node.getRight(), value));
            return node;
        }
    }

    private Node findMinElement(Node node) {
        if (node.getLeft() == null) {
            return node;
        }
        return findMinElement(node.getLeft());
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.getRight() != null
            && result.getRight().getColor() == Color.Red
            && (result.getLeft() == null || result.getLeft().getColor() == Color.Black)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.getLeft() != null
            && result.getLeft().getColor() == Color.Red
            && result.getLeft().getLeft() != null
            && result.getLeft().getLeft().getColor() == Color.Red) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.getLeft() != null
            && result.getLeft().getColor() == Color.Red
            && result.getRight() != null
            && result.getRight().getColor() == Color.Red) {
                needRebalance = true;
                colorSwap(result);
            }
        } while (needRebalance);
        return result;
    }

    private Node leftSwap(Node node) {
        Node leftChild = node.getLeft();
        Node betweenChild = leftChild.getRight();
        leftChild.setRight(node);
        node.setLeft(betweenChild);
        leftChild.setColor(node.getColor());
        node.setColor(Color.Red);
        return leftChild;
    }

    private Node rightSwap(Node node) {
        Node rightChild = node.getRight();
        Node betweenChild = rightChild.getLeft();
        rightChild.setLeft(node);
        node.setRight(betweenChild);
        rightChild.setColor(node.getColor());
        node.setColor(Color.Red);
        return rightChild;
    }

    private void colorSwap(Node node) {
        node.getRight().setColor(Color.Black);
        node.getLeft().setColor(Color.Black);
        node.setColor(Color.Red);
    }
}
