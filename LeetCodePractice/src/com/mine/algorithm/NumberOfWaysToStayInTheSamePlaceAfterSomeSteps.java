package com.mine.algorithm;

/**
 * https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/
 */
public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    int mod = (int) 1e9 + 7;

    public int numWays(int steps, int len) {
        int max = Math.min(steps / 2, len - 1);
        int[][] f = new int[steps + 1][max + 1];
        f[steps][0] = 1;
        for (int i = steps - 1; i >= 0; i--) {
            for (int j = 0; j <= max; j++) {
                f[i][j] = (f[i][j] + f[i + 1][j]) % mod;
                if (j - 1 >= 0) f[i][j] = (f[i][j] + f[i + 1][j - 1]) % mod;
                if (j + 1 <= max) f[i][j] = (f[i][j] + f[i + 1][j + 1]) % mod;
            }
        }
        return f[0][0];
    }
}

class NumberOfWaysToStayInTheSamePlaceAfterSomeStepsRun{
    public static void main(String[] args) {
        NumberOfWaysToStayInTheSamePlaceAfterSomeSteps  numberOfWaysToStayInTheSamePlaceAfterSomeSteps = new NumberOfWaysToStayInTheSamePlaceAfterSomeSteps();
        System.out.println(numberOfWaysToStayInTheSamePlaceAfterSomeSteps.numWays(3,2));
    }
}