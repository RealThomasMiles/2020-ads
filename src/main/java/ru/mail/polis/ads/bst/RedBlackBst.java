package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root = null;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);
        return x;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, RED);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        return fixUp(x);
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node removeMin(Node x) {
        if (x.left == null) return null;
        if (!isRed(x.left) && !isRed(x.left.left)) x = moveRedLeft(x);
        x.left = removeMin(x.left);
        return fixUp(x);
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node remove(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) x = moveRedLeft(x);
                x.left = remove(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
            } else if (cmp == 0 && x.right == null) return null;
            else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) x = moveRedRight(x);
                if (key.compareTo(x.key) == 0) {
                    Node min = min(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = removeMin(x.right);
                } else {
                    x.right = remove(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    private Node min(Node x) {
        Node min = x;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    private Node max(Node x){
        Node max = x;
        while (max.right != null){
            max = max.right;
        }
        return max;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        if (cmp > 0) return get(x.right, key);
        return x;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = x.key.compareTo(key);
        if (cmp == 0) return x;
        if (cmp > 0) return floor(x.left, key);
        Node right = floor(x.right, key);
        return right == null ? x : right;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) return null;
        int cmp = x.key.compareTo(key);
        if (cmp == 0) return x;
        if (cmp < 0) return ceil(x.right, key);
        Node left = ceil(x.left, key);
        return left == null ? x : left;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node x = get(root, key);
        return x == null ? null : x.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value removeValue = get(key);
        if (removeValue == null) return null;
        root = remove(root, key);
        size--;
        return removeValue;
    }

    @Nullable
    @Override
    public Key min() {
        return root == null ? null : min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        return root == null ? null : min(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        return  root == null ? null : max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return  root == null ? null : max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node x = ceil(root, key);
        return x == null ? null : x.key;
    }

    @Override
    public int size() {
        return size;
    }
}
