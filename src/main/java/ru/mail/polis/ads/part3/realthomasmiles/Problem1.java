package ru.mail.polis.ads.part3.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n + 1];
        arr[0] = 0;

        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextInt();
        }

        boolean flag = true;
        for (int i = 1; i < (n / 2); i++) {
            if (arr[i] > arr[2 * i] || arr[i] > arr[2 * i + 1]) {
                out.write("NO");
                flag = false;
                break;
            }
        }
        if (flag) {
            if (n % 2 == 0) {
                if (arr[n / 2] > arr[n]) {
                    out.write("NO");
                    flag = false;
                }
            } else {
                if (arr[n / 2] > arr[n - 1] || arr[n / 2] > arr[n]) {
                    out.write("NO");
                    flag = false;
                }
            }
        }
        if (flag) {
            out.write("YES");
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
