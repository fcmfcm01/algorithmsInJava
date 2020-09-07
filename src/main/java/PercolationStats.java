import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int[] numbersWhenPercolates;
    private int dimension;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("un-support input for n or trials.");
        }
        numbersWhenPercolates = new int[trials];
        dimension = n;
        this.trials = trials;
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int countWhenPercolates = 0;
            while (!percolation.percolates() && countWhenPercolates<Math.pow(dimension,2)) {
                int row = StdRandom.uniform(1,n+1);
                int col = StdRandom.uniform(1,n+1);
                if (percolation.isOpen(row, col)) {
                    continue;
                } else {
                    percolation.open(row, col);
                    countWhenPercolates++;
                }
            }
            numbersWhenPercolates[i] = countWhenPercolates;
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200, 100);
        System.out.println("mean\t=" + percolationStats.mean());
        System.out.println("stddev\t=" + percolationStats.stddev());
        System.out.println("95% confidence interval\t=[" + percolationStats.confidenceLo()
                + "," + percolationStats.confidenceHi() + "]");
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(numbersWhenPercolates) / Math.pow(dimension, 2);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(numbersWhenPercolates) / Math.pow(dimension, 2);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev()) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev()) / Math.sqrt(trials);
    }
}
