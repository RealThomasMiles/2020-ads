package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<K extends Comparable<K>, V>
        implements Bst<K, V> {

    private Node root = null;
    private int size = 0;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int height;

        public Node(K key, V value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private Node get(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        if (cmp > 0) return get(x.right, key);
        return x;
    }    

    private Node put(Node x, K key, V value){
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        fixHeight(x);
        x = balance(x);

        return x;
    }

    private void fixHeight(Node x) {
        x.height = 1 +
            Math.max(
                height(x.left),
                height(x.right));
    }

    private Node balance(Node x) {
        if (factor(x) == 2){
            if (factor(x.left) < 0){
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) == -2){
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }

        return x;
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node rotateRight(Node y){
        Node x = y.left;
        y.left = x.right;
        x.right = y;

        fixHeight(y);
        fixHeight(x);
        
        return x;
    }

    private Node rotateLeft(Node x){
        Node y = x.right;
        x.right = y.left;
        y.left = x;

        fixHeight(x);
        fixHeight(y);

        return y;
    }

    private Node remove(Node x, K key){
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left, key);
        } else if (cmp > 0) {
            x.right = remove(x.right, key);
        } else {
            x = innerDelete(x);
        }

        return x;
    }

    private Node innerDelete(Node x){
        if (x.right == null) return x.left;
        if (x.left == null) return x.right;
        
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;

        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;

        x.left = deleteMin(x.left);

        return x;
    }

    private Node min(Node x) {
        Node min = x;

        while (min.left != null){
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

    private Node floor(Node x, K key) {
        if (x == null) return null;
        if (key == x.key) return x;
        
        if (x.key.compareTo(key) > 0) return floor(x.left, key);
        
        Node right = floor(x.right, key);

        return right == null ? x : right;
    }    

    private Node ceil(Node x, K key) {
        if (x == null) return null;
        if (key == x.key) return x;

        if (x.key.compareTo(key) < 0) return ceil(x.right, key);

        Node left = ceil(x.left, key);

        return left == null ? x : left;
    }

    @Override
    public V get(@NotNull K key) {
        Node x = get(root, key);

        return x == null ? null : x.value;
    }

    @Override
    public void put(@NotNull K key, @NotNull V value) {
        root = put(root, key, value);
    }

    @Override
    public V remove(@NotNull K key) {
        Node x = get(root, key);
        if (x == null) return null;
        
        root = remove(root, key);
        size--;

        return x.value;
    }

    @Override
    public K min() {
        return root == null ? null : min(root).key;
    }

    @Override
    public V minValue() {
        return root == null ? null : min(root).value;
    }

    @Override
    public K max() {
        return  root == null ? null : max(root).key;
    }

    @Override
    public V maxValue() {
        return  root == null ? null : max(root).value;
    }

    @Override
    public K floor(@NotNull K key) {
        Node x = floor(root, key);

        return x == null ? null : x.key;
    }

    @Override
    public K ceil(@NotNull K key) {
        Node x = ceil(root, key);

        return x == null ? null : x.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x){
        return x == null ? 0 : x.height;
    }
}
