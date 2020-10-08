package ru.mail.polis.ads.part3.realthomasmiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Problem2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
		Heap heap = new Heap();
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			if (in.nextInt() == 0) {
				heap.insert(in.nextInt());
			} else {
				out.println(heap.extract());
			}
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

class Heap {
    private List<Integer> arr;
	private int size;

	public Heap() {
		this.size = 0;
		arr = new ArrayList<>();
		arr.add(Integer.MAX_VALUE);
	}

	private int getParentPos(int pos) {
		return pos / 2;
	}

	private int getLeftChildPos(int pos) {
		return pos * 2;
	}

	private int getRightChildPos(int pos) {
		return pos * 2 + 1;
	}

	public void insert(int element) {
		arr.add(element);
		swim(++size);
	}

	public int extract() {
		int popped = arr.get(1);
		if(size > 1) {
			arr.set(1, arr.remove(size--));
			sink(1);
		} else {
			arr.remove(size--);
		}
		return popped;
	}

	private void swim(int pos) {
		while (arr.get(pos) > arr.get(getParentPos(pos))) {
			swap(pos, getParentPos(pos));
			pos = getParentPos(pos);
		}
	}

	private void sink(int pos) { 
		if (pos > (size / 2)) {
			return;
		}

		int leftChildPos = getLeftChildPos(pos);
		int rightChildPos = getRightChildPos(pos);
		if (rightChildPos > size) {
			rightChildPos = leftChildPos;
		}

		if (arr.get(pos) < arr.get(getLeftChildPos(pos)) || arr.get(pos) < arr.get(rightChildPos)) { 
			if (arr.get(leftChildPos) >= arr.get(rightChildPos)) { 
				swap(pos, leftChildPos);
				sink(leftChildPos);
			} else {
				swap(pos, rightChildPos);
				sink(rightChildPos);
			}
		}
	}

	private void swap(int pos1, int pos2) {
		int tmp;
		tmp = arr.get(pos1);
		arr.set(pos1, arr.get(pos2));
		arr.set(pos2, tmp);
	}
}
