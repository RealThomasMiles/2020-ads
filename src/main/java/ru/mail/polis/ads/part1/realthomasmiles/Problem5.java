package ru.mail.polis.ads.part1.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.EmptyStackException;

public class Problem5 {
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
        Queue<Integer> queue = new Queue<>();
        String command;
        String answer;
        do {
            command = in.next();
            int res, num;
            switch (command) {
                case "pop": {
                    res = queue.pop();
                    answer = String.valueOf(res);
                    break;
                }
                case "front": {
                    res = queue.peek();
                    answer = String.valueOf(res);
                    break;
                }
                case "size": {
                    answer = Integer.toString(queue.size());
                    break;
                }
                case "clear": {
                    queue.clear();
                    answer = "ok";
                    break;
                }
                case "exit": {
                    answer = "bye";
                    break;
                }
                default: {
                    num = in.nextInt();
                    queue.push(num);
                    answer = "ok";
                    break;
                }
            }
            System.out.println(answer);
        } while (!command.equals("exit"));
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