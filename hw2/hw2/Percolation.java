package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Assert;
import org.junit.Assert.*;

public class Percolation {
    private boolean[][] items;
    private WeightedQuickUnionUF grid;
    private int n;
    private int open;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N should > 0");
        }
        items = new boolean[N][N];
        grid = new WeightedQuickUnionUF(N * N + 2);
        for (boolean[] item : items) {
            for (boolean i : item) {
                i = false;
            }
        }
        n = N;
    }

    // transfer row and collum into 1d set for WeightedQuickUnionUF
    public int xyTo1D(int r, int c) {
        int num = r * n + c;
        return num;
    }

    // union if have neighboring open sites
    private void checkNeighbor(int row, int col) {
        int s = xyTo1D(row, col);
        if (row == 0) {
            grid.union(s, n * n);
        }
        if (row == n - 1) {
            grid.union(s, n * n + 1);
        }
        if (row > 0) {
            if (isOpen(row - 1, col)) {
                grid.union(s, s - n);
            }
        }
        if (row < n - 1) {
            if (isOpen(row + 1, col)) {
                grid.union(s, s + n);
            }
        }
        if (col > 0) {
            if (isOpen(row, col - 1)) {
                grid.union(s, s - 1);
            }
        }
        if (col < n - 1) {
            if (isOpen(row, col + 1)) {
                grid.union(s, s + 1);
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > n - 1 || row < 0 || col > n - 1 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("check row and collum number");
        }
        if (!isOpen(row, col)) {
            items[row][col] = true;
            checkNeighbor(row, col);
            open += 1;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > n - 1 || row < 0 || col > n - 1 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("check row and collum number");
        }
        return items[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row > n - 1 || row < 0 || col > n - 1 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("check row and collum number");
        }
        int s = xyTo1D(row, col);
        return grid.connected(s, n * n);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return open;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(n * n, n * n + 1);
    }


    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(3, 4);
        p.open(2, 4);
        Assert.assertTrue(p.isOpen(3, 4));
        Assert.assertEquals(2, p.numberOfOpenSites());
        Assert.assertFalse(p.isFull(2, 4));
        p.open(2, 2);
        p.open(2, 3);
        Assert.assertTrue(p.isOpen(2, 2));
        Assert.assertFalse(p.isFull(2, 2));
        p.open(0, 2);
        p.open(1, 2);
        Assert.assertTrue(p.isFull(1, 2));
        Assert.assertTrue(p.isFull(3, 4));
        Assert.assertFalse(p.percolates());
        p.open(4, 4);
        Assert.assertTrue(p.percolates());
    }


}