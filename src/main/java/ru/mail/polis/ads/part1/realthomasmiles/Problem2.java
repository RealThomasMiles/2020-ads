package ru.mail.polis.ads.part1.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Problem2 {

    private static String breadthFirstSearch(Node head){
        Queue<Node> queue = new Queue<>();
        StringBuilder outputString = new StringBuilder();
        queue.push(head);
        while (queue.size() != 0){
            Node operator = queue.pop();
            if (operator.right != null) {
                queue.push(operator.right);
                queue.push(operator.left);
            }
            outputString = outputString.insert(0, operator.op);
        }
        return outputString.toString();
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        for (int i = 0; i < count; i++) {
            char[] charsPostfix = in.next().toCharArray();
            Stack<Node> stack = new Stack<>();
            for (char item : charsPostfix) {
                if (Character.isLowerCase(item)) {
                    stack.push(new Node(item, null, null));
                } else {
                    Node left = stack.pop();
                    Node right = stack.pop();
                    stack.push(new Node(item, left, right));
                }
            }
            String output = breadthFirstSearch(stack.pop());
            out.println(output);
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

class Node {
    char op;
    Node left;
    Node right;

    public Node(char op, Node left, Node right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }
}

class Queue<E> {
    private int size;    
    private Node<E> head;
    private Node<E> last;

    Queue() {
        this.size = 0;        
        this.head = null;
        this.last = null;
    }

    private static class Node<E> {
        E content;
        Node<E> prev;
        Node(Node<E> prev, E element) {
            this.content = element;
            this.prev = prev;
        }
    }

    public void push(E element){
        if (size == 0) {
            this.head = this.last = new Node<>(null, element);
        } else {
            this.last.prev = new Node<>(null, element);
            this.last = this.last.prev;
        }
        size++;
    }

    public E pop() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        Node<E> currentFirst = this.head;
        this.head = this.head.prev;
        size--;
        return currentFirst.content;
    }

    public E peek() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        return this.head.content;
    }

    public int size() {
        return size;
    }

    public void clear() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }
}

class Stack<E> {
    private int size;
    ArrayList<E> array;

    Stack() {
        this.size = 0;
        array = new ArrayList<>();
    }

    public void push(E element){
        array.add(element);
        size++;
    }

    public E pop() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        E element = array.get(this.size - 1);
        array.remove(this.size - 1);
        this.size--;
        return element;
    }

    public E peek() throws EmptyStackException {
        if (this.size == 0)
            throw new EmptyStackException();
        return array.get(this.size - 1);
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        array.clear();
        this.size = 0;
    }
}