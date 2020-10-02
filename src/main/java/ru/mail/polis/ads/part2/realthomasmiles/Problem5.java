package ru.mail.polis.ads.part2.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }

        mergeSort(arr, n);

        StringBuilder output = new StringBuilder();
        for (int[] item : arr) {
            output.append(item[0]).append(" ").append(item[1]).append('\n');
        }
        out.write(output.toString());
    }

    private static void mergeSort(int[][] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[][] l = new int[mid][2];
        int[][] r = new int[n - mid][2];
     
        for (int i = 0; i < mid; i++) {
            l[i][0] = a[i][0];
            l[i][1] = a[i][1];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid][0] = a[i][0];
            r[i - mid][1] = a[i][1];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);
     
        merge(a, l, r, mid, n - mid);
    }

    private static void merge(int[][] a, int[][] l, int[][] r, int left, int right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left && j < right) {
            if (l[i][0] <= r[j][0]) {
                a[k][0] = l[i][0];
                a[k][1] = l[i][1];
                k++;
                i++;
            }
            else {
                a[k][0] = r[j][0];
                a[k][1] = r[j][1];
                k++;
                j++;
            }
        }
        while (i < left) {
            a[k][0] = l[i][0];
            a[k][1] = l[i][1];
            k++;
            i++;
        }
        while (j < right) {
            a[k][0] = r[j][0];
            a[k][1] = r[j][1];
            k++;
            j++;
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
