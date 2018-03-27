package dataStructures;

/*
* Range sum query problem generalized to 2 dimensional matrix
* https://leetcode.com/problems/range-sum-query-2d-mutable/
 */
class FenwickTree2D {
    private int[][] vals;
    private int n, m;
    private int[][] parts;

    public FenwickTree2D(int[][] matrix) {
        n = matrix.length;
        if(n == 0) return;

        m = matrix[0].length;
        vals = new int[n][m];
        parts = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int delta = val - vals[row][col];
        vals[row][col] = val;
        for(int i = row; i < n; i = i | (i + 1)) {
            for(int j = col; j < m; j = j | (j + 1)) {
                parts[i][j] += delta;
            }
        }
    }

    private int sum(int row, int col) {
        int sum = 0;
        for(int i = row; i >= 0; i = (i & (i + 1)) - 1) {
            for(int j = col; j >= 0; j = (j & (j + 1)) - 1) {
                sum += parts[i][j];
            }
        }
        return sum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum(row2, col2) - sum(row1 - 1, col2) - sum(row2, col1 - 1) + sum(row1 - 1, col1 - 1);
    }
}
