package ru.mail.polis.ads.part5.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String str1 = in.next();
        String str2 = in.next();
        boolean[][] d = new boolean[str1.length() + 1][str2.length() + 2];

        d[0][0] = true;
        for (int i = 1; i <= str1.length(); i++) {
            char c1 = str1.charAt(i - 1);
            for (int j = 1; j <= str2.length(); j++) {
                char c2 = str2.charAt(j - 1);
                if (c1 == c2 || c1 == '?' || c2 == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (c1 == '*' || c2 == '*') {
                    d[i][j] = d[i][j - 1] || d[i - 1][j - 1] || d[i - 1][j];
                }
            }
        }

        out.println(d[str1.length()][str2.length()] ? "YES" : "NO");
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
