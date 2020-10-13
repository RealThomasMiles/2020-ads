package ru.mail.polis.ads.part3.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        
        out.println(findMax(arr, 0, arr[n - 1] - arr[0] + 1, k));
    }

    private static int findMax(int[] arr, int l, int r, int k) {
        if (r - l <= 1) {
            return l;
        }

        int pivot = (l + r) / 2;
        int count = 1;
        int prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - prev >= pivot) {
                count++;
                prev = arr[i];
            }
        }

        if (count >= k) {
            return findMax(arr, pivot, r, k);
        } else {
            return findMax(arr, l, pivot, k);
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
