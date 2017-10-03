package dataStructures;

public class FenwickTree {
    private int[] partialSum;
    public int n;

    public FenwickTree(int n) {
        this.n = n;
        partialSum = new int[n + 1];
    }

    public void update(int index, int delta) {
        while (index <= n) {
            partialSum[index] += delta;
            index = index | (index + 1);
        }
    }

    public int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += partialSum[index];
            index = (index & (index + 1)) - 1;
        }
        return sum;
    }
}
