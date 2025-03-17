import java.util.Arrays;

public class Weakqueen {

    public static boolean isSafe(int[] board, int row, int col, int n, int w) {
        for (int i = 0; i < row; i++) {
            int prevCol = board[i];
            int verticalLimit = n - 1 - w;
            if (prevCol == col) return false;
            if (Math.abs(prevCol - col) == Math.abs(i - row) && Math.abs(i - row) <= verticalLimit) 
                return false;
        }
        return true;
    }

    public static int countWays(int[] board, int row, int n, int w) {
        if (row == n) return 1;
        int count = 0;
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n, w)) {
                board[row] = col;
                count += countWays(board, row + 1, n, w);
                board[row] = -1;
            }
        }
        return count;
    }

    public static int calculateS(int n) {
        int sum = 0;
        for (int w = 0; w < n; w++) {
            int[] board = new int[n];
            Arrays.fill(board, -1);
            sum += countWays(board, 0, n, w);
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 14;
        System.out.println("S(" + n + ") = " + calculateS(n));
    }
}
