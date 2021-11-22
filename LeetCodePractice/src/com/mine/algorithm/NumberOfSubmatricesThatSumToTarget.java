package com.mine.algorithm;

/**
 * Created: 2021/05/29 15:49
 *
 * https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target/
 */
public class NumberOfSubmatricesThatSumToTarget {
    /**
     * 朴素二维前缀和
     *
     * 时间复杂度：O(m^2 * n^2)
     * 空间复杂度：O(m * n)
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int ans = 0, n = matrix.length, m = matrix[0].length;
        int[][] sums = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sums[i][j] = sums[i][j - 1] + sums[i - 1][j] - sums[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int p = 1; p <= i; p++) {
                    for (int q = 1; q <= j; q++) {
                        if (sums[i][j] - sums[p - 1][j] - sums[i][q - 1] + sums[p - 1][q - 1] == target) ans++;
                    }
                }
            }
        }
        return ans;
    }
}

class NumberOfSubmatricesThatSumToTargetRun{
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0,1,0},{1,1,1},{0,1,0}};
        int target = 0;
        NumberOfSubmatricesThatSumToTarget number = new NumberOfSubmatricesThatSumToTarget();
        System.out.println(number.numSubmatrixSumTarget(matrix, target));
    }
}
