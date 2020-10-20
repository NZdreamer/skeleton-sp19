package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int t;
    private int opened;
    private double[] fractions;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        t = T;
        fractions = new double[T];
        Percolation p = pf.make(N);
        for (int i = 0; i < T; i++) {
            int randomR = StdRandom.uniform(0, N);
            int randomC = StdRandom.uniform(0, N);
            if (!p.isOpen(randomR, randomC)) {
                p.open(randomR, randomC);
                opened += 1;
            }
            fractions[i] = (double) opened / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
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

