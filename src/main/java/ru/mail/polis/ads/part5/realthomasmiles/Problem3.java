package ru.mail.polis.ads.part5.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = in.nextInt();
        }
        int[] d = new int[n];
        d[0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (seq[j] == 0) {
                    continue;
                }
                if (((seq[i] % seq[j] == 0) && (d[j] > d[i]))) {
                    d[i] = d[j];
                }
            }
            d[i]++;
        }

        int max = d[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, d[i]);
        }
        out.print(max);
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
