package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int n;
    private int t;
    private int[] x;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        n = N;
        t = T;
        x = new int[N];
        Percolation p = pf.make(N);
        for (int i = 0; i < T; i++) {
            int randomR = StdRandom.uniform(0, N);
            int randomC = StdRandom.uniform(0, N);
            if (!p.isOpen(randomR, randomC)) {
                p.open(randomR, randomC);
                x[i] = p.xyTo1D(randomR, randomC);
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        int sum = 0;
        for (int xi : x) {
            sum += xi;
        }
        return (double) sum / (double) t;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        int sum = 0;
        for (int xi : x) {
            sum += (xi - this.mean()) * (xi - this.mean());
        }
        double dev = (double) sum / (double) (t - 1);
        return Math.sqrt(dev);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return t - 1.96 * this.stddev() / Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return t + 1.96 * this.stddev() / Math.sqrt(t);
    }
}
