package ru.mail.polis.ads.part5.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();

        double l = 0;
        double r = 10e5;
        while (Math.abs(r - l) > 1e-10) {
            double x = (l + r) / 2;
            if (x*x + Math.sqrt(x) - c > 0) {
                r = x;
            } else {
                l = x;
            }
        }
        out.println(r);
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
