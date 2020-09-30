package ru.mail.polis.ads.part2.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class Problem4 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int k = in.nextInt();
        String[] input = in.readLine().split(" ");
        BigInteger[] arr = new BigInteger[input.length];
        for (int i = 0; i < input.length; i++) {
            arr[i] = new BigInteger(input[i]);
        }
        out.write(kthSmallest(arr, 0, arr.length - 1, k).toString());
    }

    public static BigInteger kthSmallest(BigInteger[] arr, int l, int r, int k) 
    { 
        int pos = partition(arr, l, r);
        
        if (pos - l == k - 1)
            return arr[pos];
        if (pos - l > k - 1)
            return kthSmallest(arr, l, pos - 1, k);
        
        return kthSmallest(arr, pos + 1, r, k - pos + l - 1);
    }

    public static int partition(BigInteger[] arr, int l, int r) {
        BigInteger x = arr[r];
        int i = l;

        for (int j = l; j <= r - 1; j++) {
            if (arr[j].compareTo(x) >= 0) {
                BigInteger temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
  
                i++; 
            } 
        }

        BigInteger temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;

        return i;
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

        String readLine() throws IOException {
            return reader.readLine();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
