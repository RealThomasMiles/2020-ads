package ru.mail.polis.ads.part4.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem1 {
    private static int[][] arrPrices;
    private static int[][] arrPivots;

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.next();
        arrPrices = new int[input.length()][input.length()];
        arrPivots = new int[input.length()][input.length()];

        getPrices(input);
        out.write(balance(input, 0, input.length() - 1));
    }

    private static void getPrices(String input) {
        for (int i = 0; i < input.length(); i++) {
            arrPrices[i][i] = 1;
        }
        for (int i = 0; i < input.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                int min = i - j + 1;
                arrPivots[j][i] = j;
                if (input.charAt(j) == '(' && input.charAt(i) == ')' || input.charAt(j) == '[' && input.charAt(i) == ']') {
                    min = arrPrices[j + 1][i - 1];
                    arrPivots[j][i] = -1;
                }
                for (int k = j; k < i; ++k) {
                    if ((arrPrices[j][k] + arrPrices[k + 1][i]) < min) {
                        min = arrPrices[j][k] + arrPrices[k + 1][i];
                        arrPivots[j][i] = k;                        
                    }
                }
                arrPrices[j][i] = min;
            }
        }
    }

    private static String balance(String input, int start, int end) {
        if (start == end) {
            if ((input.charAt(start) == '(') || (input.charAt(start) == ')')) {
                return "()";
            } else {
                return "[]";
            }
        }
        if (arrPrices[start][end] == 0) {
            return input.substring(start, end + 1);
        }
        if (arrPivots[start][end] == -1) {
            return input.substring(start, start + 1) + balance(input, start + 1, end - 1) + input.substring(end, end + 1);
        }
        return balance(input, start, arrPivots[start][end]) + balance(input, arrPivots[start][end] + 1, end);
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
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
