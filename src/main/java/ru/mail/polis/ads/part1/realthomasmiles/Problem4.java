package ru.mail.polis.ads.part1.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.EmptyStackException;
import java.util.ArrayList;

public class Problem4 {
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
        Stack<Integer> stack = new Stack<Integer>();
        String command;
        String answer;
        do {
            command = in.next();
            try {
                int res;
                int num;
                switch (command) {
                    case "pop":
                        res = stack.pop();
                        answer = String.valueOf(res);
                        break;
                    case "back":
                        res = stack.peek();
                        answer = String.valueOf(res);
                        break;
                    case "size":
                        answer = Integer.toString(stack.size());
                        break;
                    case "clear":
                        stack.clear();
                        answer = "ok";
                        break;
                    case "exit":
                        answer = "bye";
                        break;
                    default:
                        num = in.nextInt();
                        stack.push(num);
                        answer = "ok";
                        break;
                }
            } catch (EmptyStackException err) {
                answer = "error";
            }
            System.out.println(answer);
        } while (!command.equals("exit"));
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