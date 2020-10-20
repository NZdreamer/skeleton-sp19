package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int t;
    private double[] fractions;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        t = T;
        fractions = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            int opened = 0;
            while (!p.percolates()) {
                int randomR = StdRandom.uniform(0, N);
                int randomC = StdRandom.uniform(0, N);
                if (!p.isOpen(randomR, randomC)) {
                    p.open(randomR, randomC);
                    opened += 1;
                }
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
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }
}

