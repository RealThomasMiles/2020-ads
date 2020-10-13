package ru.mail.polis.ads.part3.realthomasmiles;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Problem3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        MinHeap minHeap = new MinHeap();
        MaxHeap maxHeap = new MaxHeap();
        int input;
        int median = 0;
        if (in.hasNext()) {
            input = in.nextInt();
            minHeap.insert(input);
            median = input;
            out.println(String.valueOf(median));
        }
        while (in.hasNext()) {
            input = in.nextInt();
            if (input > median) {
                minHeap.insert(input);
            } else {
                maxHeap.insert(input);
            }
            if (Math.abs(minHeap.size() - maxHeap.size()) > 1) {
                if (minHeap.size() > maxHeap.size()) {
                    maxHeap.insert(minHeap.extract());
                } else {
                    minHeap.insert(maxHeap.extract());
                }
            }
            if (minHeap.size() == maxHeap.size()) {
                median = (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                median = minHeap.size() > maxHeap.size() ? minHeap.peek() : maxHeap.peek();
            }
            out.println(String.valueOf(median));
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

        boolean hasNext() {
            try {
                return reader.ready();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}

abstract class AbsHeap {
    protected List<Integer> arr;
    protected int size;
    
    public AbsHeap() {
		this.size = 0;
		arr = new ArrayList<>();
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
    
    public void insert(int element) {
		arr.add(element);
		swim(++size);
    }
    
    public int peek() {
        return arr.get(1);
    }

    public int size() {
        return size;
    }
    
    protected int getParentPos(int pos) {
		return pos / 2;
    }
    
    protected int getLeftChildPos(int pos) {
		return pos * 2;
	}

    protected abstract void swim(int pos);
    
    protected abstract void sink(int pos);

	protected void swap(int pos1, int pos2) {
		int tmp;
		tmp = arr.get(pos1);
		arr.set(pos1, arr.get(pos2));
		arr.set(pos2, tmp);
	}
}

class MaxHeap extends AbsHeap {
	public MaxHeap() {
        super();
        arr.add(Integer.MAX_VALUE);
	}

	protected void swim(int pos) {
		while (arr.get(pos) > arr.get(getParentPos(pos))) {
			swap(pos, getParentPos(pos));
			pos = getParentPos(pos);
		}
    }
    
    protected void sink(int pos) { 
		while (getLeftChildPos(pos) <= size) {
            int leftChildPos = getLeftChildPos(pos);
            if (leftChildPos < size && arr.get(leftChildPos) < arr.get(leftChildPos + 1)) {
                leftChildPos++;
            }
            if (arr.get(pos) >= arr.get(leftChildPos)) {
                break;
            }
            swap(pos, leftChildPos);
            pos = leftChildPos;
        }
	}
}

class MinHeap extends AbsHeap {
	public MinHeap() {
        super();
        arr.add(Integer.MIN_VALUE);
	}

	protected void swim(int pos) {
		while (arr.get(pos) < arr.get(getParentPos(pos))) {
			swap(pos, getParentPos(pos));
			pos = getParentPos(pos);
		}
    }
    
    protected void sink(int pos) { 
		while (getLeftChildPos(pos) <= size) {
            int leftChildPos = getLeftChildPos(pos);
            if (leftChildPos < size && arr.get(leftChildPos) > arr.get(leftChildPos + 1)) {
                leftChildPos++;
            }
            if (arr.get(pos) <= arr.get(leftChildPos)) {
                break;
            }
            swap(pos, leftChildPos);
            pos = leftChildPos;
        }
	}
}
