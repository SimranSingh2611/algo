package dataStructures;

public class FenwickTree {
    private int[] array;
    private int[] partialSum;
    public int n;
    public FenwickTree(int[] array) {
        n = array.length;
        this.array = array;
        partialSum = new int[n];
        for(int i = 1; i <= n; i++) update(i, array[i - 1]);
    }

    public void update(int index, int delta) {
        while(index <= n) {
            // to go back to zero-based
            partialSum[index - 1] += delta;
            index += index & -index;
        }
    }

    public int query(int index) {
        int sum = 0;
        while(index > 0) {
            // to go back to zero-based
            sum += partialSum[index - 1];
            index -= index & -index;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        FenwickTree ft = new FenwickTree(a);
        System.out.println(ft.query(5));
        System.out.println(ft.query(10));
    }
}
