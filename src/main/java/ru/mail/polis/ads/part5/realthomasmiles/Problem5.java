package ru.mail.polis.ads.part5.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        if (n == 1) {
            out.print(1);
            return;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        for (int i = 0; i < n; i++)
            out.write(arr[i] + " ");
        out.println();
        int i = n - 1;
        while(i > 0) {
            if(arr[i - 1] > arr[i]) {
                i--;
                continue;
            }
            int j = n - 1;
            while (arr[i - 1] > arr[j]) {
                j--;
            }
            int tmp = arr[i - 1];
            arr[i - 1] = arr[j];
            arr[j] = tmp;

            int[] reverseTail = Arrays.copyOfRange(arr, i, n);
            j = reverseTail.length - 1;
            for (int k = i; k < n; k++) {
                arr[k] = reverseTail[j--];
            }
            i = n - 1;
            for (int k = 0; k < n; k++)
                out.write(arr[k] + " ");
            out.println();
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
