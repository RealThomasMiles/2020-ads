package ru.mail.polis.ads.part3.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        for (int i = 0; i < q; i++) {
            if (binarySearch(arr, in.nextInt(), 0, arr.length - 1)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }

    private static boolean binarySearch(int[] arr, int x, int start, int end) {
        if (start == end) {
            return arr[start] == x;
        }
        int pivot = start + (end - start) / 2;
        if (x > arr[pivot]) {
            return binarySearch(arr, x, pivot + 1, end);
        } else {
            return binarySearch(arr, x, start, pivot);
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
