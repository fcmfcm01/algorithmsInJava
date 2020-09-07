import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * The Percolation class models a percolation system.
 */
public class Percolation {
    private boolean[][] grid; // grid[i][j] = site at row i, column j
    private int len; // length of the grid
    private int top = 0; // virtual top
    private int bottom; // virtual bottom
    private WeightedQuickUnionUF uf; // a WeightedQuickUnionUF instance

    /**
     * Create a N-by-N grid, with all sites blocked
     */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        len = N;
        grid = new boolean[N][N];
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
    }

    public static void main(String[] args) {

    }

    /**
     * Open site (row i, column j) if it is not open already, also union sites
     */
    public void open(int row, int col) {
        if (row < 1 || row > len || col < 1 || col > len) {
            throw new IllegalArgumentException("row and col value should not smaller than 1.");
        }
        grid[row - 1][col - 1] = true;

        if (row == 1) { // union virtual top
            uf.union(getSiteIndex(row, col), top);
        }
        if (row == len) { // union virtual bottom
            uf.union(getSiteIndex(row, col), bottom);
        }

        // union possible neighbor(s)
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(getSiteIndex(row, col), getSiteIndex(row - 1, col));
        }
        if (row < len && isOpen(row + 1, col)) {
            uf.union(getSiteIndex(row, col), getSiteIndex(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(getSiteIndex(row, col), getSiteIndex(row, col - 1));
        }
        if (col < len && isOpen(row, col + 1)) {
            uf.union(getSiteIndex(row, col), getSiteIndex(row, col + 1));
        }
    }

    /**
     * Is site (row i, column j) open?
     *
     * @return true if site (row i, column j) is open; false otherwise
     * @throws java.lang.IllegalArgumentException unless both 1<= i <= N and 1 <= j <= N
     */
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException("row and col value should not smaller than 1.");
        }
        if (row > len || col > len) {
            throw new IllegalArgumentException("out of range");
        }
        return grid[row - 1][col - 1];
    }

    /**
     * Is site (row i, column j) full?
     *
     * @return true if site (row i, column j) is full; false otherwise
     * @throws java.lang.IllegalArgumentException unless both 1<= i <= N and 1 <= j <= N
     */
    public boolean isFull(int row, int col) {
        if (row < 1 || row > len || col < 1 || col > len) throw new IllegalArgumentException();
        return uf.connected(getSiteIndex(row, col), top);
    }

    /**
     * Does the system percolate?
     *
     * @return true of the system percolates; false otherwise
     */
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    /**
     * Return the index at site(i, j)
     *
     * @return index at site(i, j)
     */
    private int getSiteIndex(int i, int j) {
        return len * (i - 1) + j;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int numberOfOpenSites = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j]) {
                    numberOfOpenSites++;
                }
            }
        }
        return numberOfOpenSites;
    }
}
