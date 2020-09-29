package ru.mail.polis.ads.part1.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String brackets = in.next();
        int counter = 0;
        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) == '(')
                counter++;
            else {
                counter--;
                if (counter < 0) break;
            }
        }
        if (counter == 0) {
            out.write("YES" + '\n');
        } else {
            out.write("NO" + '\n');
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
