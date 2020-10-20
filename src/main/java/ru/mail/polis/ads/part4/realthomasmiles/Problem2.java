package ru.mail.polis.ads.part4.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int rows = in.nextInt();
        int columns = in.nextInt();
        int[][] floor = new int[rows + 1][columns + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < columns; j++) {
                floor[i][j] = in.nextInt();
            }
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = columns - 1; j >= 0; j--) {
                floor[i][j] += Math.max(floor[i - 1][j], floor[i][j + 1]);
            }
        }
        
        int rowsIterator = rows;
        int columnsIterator = 0;
        while (rowsIterator > 1 && columnsIterator < (columns - 1)) {
            if (floor[rowsIterator][columnsIterator + 1] > floor[rowsIterator - 1][columnsIterator]) {
                out.print('R');
                columnsIterator++;
            } else {
                out.print('F');
                rowsIterator--;
            }
        }
        while (rowsIterator != 1) {
            out.print('F');
            rowsIterator--;
        }
        while (columnsIterator != columns - 1) {
            out.print('R');
            columnsIterator++;
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
